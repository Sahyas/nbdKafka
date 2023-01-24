package com.nbd.repository.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public enum Topics {
    RENT_TOPIC("rents"),
    CONSUMER_GROUP_NAME("consumergroup");

    private String topic;

    Topics(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void createTopic() throws InterruptedException{
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"kafka1:9192,kafka2:9292,kafka3:9392");
        Integer partitionsNumber = 3;
        short replicationFactor = 3;
        try(Admin admin = Admin.create(properties)) {
            NewTopic newTopic = new NewTopic(Topics.RENT_TOPIC.getTopic(), partitionsNumber,replicationFactor);
            CreateTopicsOptions options = new CreateTopicsOptions().timeoutMs(1000).validateOnly(false).retryOnQuotaViolation(true);
            CreateTopicsResult result = admin.createTopics(List.of(newTopic),options);
            KafkaFuture<Void> futureResult = result.values().get(Topics.RENT_TOPIC.getTopic());
            futureResult.get();
        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        }
    }
}
