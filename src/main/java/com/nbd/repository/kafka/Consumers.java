package com.nbd.repository.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbd.model.Adult;
import com.nbd.model.Child;
import com.nbd.model.Rent;
import com.nbd.repository.mongo.RentMongoRepository;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.UUIDDeserializer;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumers {
    RentMongoRepository rentMongoRepository = new RentMongoRepository();
    List<KafkaConsumer<UUID,String>> consumersGroup = new ArrayList<>();
    private static final String BOOTSTRAP_SERVERS_CONFIG = "kafka1:9192,kafka2:9292,kafka3:9392";
    private static final Integer NUMBER_OF_CONSUMERS = 2;

    public void initConsumers(){
        Properties consumerConfig =new Properties();
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, UUIDDeserializer.class.getName());
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, Topics.CONSUMER_GROUP_NAME.getTopic());
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        for (int i = 0; i < NUMBER_OF_CONSUMERS; i++) {
            KafkaConsumer<UUID,String> kafkaConsumer = new KafkaConsumer(consumerConfig);
            kafkaConsumer.subscribe(Collections.singletonList(Topics.RENT_TOPIC.getTopic()));
            consumersGroup.add(kafkaConsumer);
            System.out.println("Consumer" + i + " created");
        }
    }

    public void consumeTopicsByGroup(Integer timeInMilis) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_CONSUMERS);
        for (KafkaConsumer<UUID,String> consumer : consumersGroup) {
            executorService.execute(() -> consume(consumer));
        }
        Thread.sleep(timeInMilis);
        for (KafkaConsumer<UUID,String> consumer : consumersGroup) {
            consumer.wakeup();
        }
        executorService.shutdown();
    }

    private void consume(KafkaConsumer<UUID,String> consumer) {
        List<String> values = new ArrayList<>();
        try {
            consumer.poll(0);
            Set<TopicPartition> consumerAssignment = consumer.assignment();
            System.out.println(consumer.groupMetadata().memberId() + " " + consumerAssignment);
            consumer.seekToBeginning(consumerAssignment);
            Duration timeout = Duration.of(100, ChronoUnit.MILLIS);
            MessageFormat formatter = new MessageFormat("Konsument {5}, Temat{0}, partycja {1}, offset {2, number, integer}, klucz {3}, wartość {4}");
            while (true) {
                ConsumerRecords<UUID,String> records = consumer.poll(timeout);
                for(ConsumerRecord<UUID,String> record : records) {
                    String result = formatter.format(new Object[]{
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.key(),
                            record.value(),
                            consumer.groupMetadata().memberId()
                    });
                    values.add(record.value());
                    System.out.println(result);
                }
            }
        } catch (WakeupException we) {
            System.out.println("Job ended");
            rentMongoRepository.clearDatabase();
            values.forEach(rent -> rentMongoRepository.add(getRentFromStringValue(rent)));
        }
    }

    public Rent getRentFromStringValue(String value) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerSubtypes(Adult.class, Child.class);
        Rent rent;
        try {
            rent = mapper.readValue(value, Rent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return rent;
    }


}