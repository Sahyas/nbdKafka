package com.nbd.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;
import com.nbd.repository.kafka.Producer;
import com.nbd.repository.kafka.Topics;
import com.nbd.repository.mongo.RentMongoRepository;
import com.nbd.repository.redis.RentRedisRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class RentRepository implements RepositoryInterface<Rent> {
    private RentMongoRepository rentMongoRepository;
    private RentRedisRepository rentRedisRepository;
    private Producer producer;

    public RentRepository(RentMongoRepository rentMongoRepository, RentRedisRepository rentRedisRepository) {
        this.rentMongoRepository = rentMongoRepository;
        this.rentRedisRepository = rentRedisRepository;
        try {
            Topics.RENT_TOPIC.createTopic();
            this.producer = new Producer();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Rent findByBook(Book book) {
        return rentMongoRepository.findByBook(book);
    }

    public Rent findByClient(Client client) {
        return rentMongoRepository.findByClient(client);
    }

    @Override
    public Rent add(Rent entity) {
        rentRedisRepository.add(entity);
        try {
            producer.send(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return rentMongoRepository.add(entity);
    }

    @Override
    public Optional<Rent> getById(UUID id) {
        Rent rent = null;
        if (rentRedisRepository.checkConnection()) {
            rent = rentRedisRepository.getById(id).orElse(null);
        }
        if (rent == null) {
            return rentMongoRepository.getById(id);
        }
        return Optional.of(rent);
    }

    @Override
    public void delete(UUID id) {
        rentRedisRepository.delete(id);
        rentMongoRepository.delete(id);
    }

    @Override
    public Rent update(Rent entity) {
        rentRedisRepository.update(entity);
        return rentMongoRepository.update(entity);
    }

    @Override
    public long size() {
        return findAll().size();
    }

    @Override
    public List<Rent> findAll() {
        List<Rent> rents = new ArrayList<>();
        if (rentRedisRepository.checkConnection()) {
            List<Rent> found = rentRedisRepository.findAll();
            rents.addAll(found);
        } else {
            List<Rent> found = rentMongoRepository.findAll();
            rents.addAll(found);
        }
        return rents;
    }
}
