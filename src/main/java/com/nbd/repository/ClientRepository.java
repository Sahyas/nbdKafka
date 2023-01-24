package com.nbd.repository;

import com.nbd.model.Client;
import com.nbd.repository.mongo.ClientMongoRepository;
import com.nbd.repository.redis.ClientRedisRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class ClientRepository implements RepositoryInterface<Client> {
    private ClientRedisRepository clientRedisRepository;
    private ClientMongoRepository clientMongoRepository;

    public ClientRepository(ClientRedisRepository clientRedisRepository, ClientMongoRepository clientMongoRepository) {
        this.clientRedisRepository = clientRedisRepository;
        this.clientMongoRepository = clientMongoRepository;
    }

    @Override
    public Client add(Client entity) {
         clientRedisRepository.add(entity);
         return clientMongoRepository.add(entity);
    }

    @Override
    public Optional<Client> getById(UUID id) {
        Client client = null;
        if (clientRedisRepository.checkConnection()) {
            client = clientRedisRepository.getById(id).orElse(null);
        }
        if (client == null) {
            return clientMongoRepository.getById(id);
        }
        return Optional.of(client);
    }

    @Override
    public void delete(UUID id) {
        clientRedisRepository.delete(id);
        clientMongoRepository.delete(id);
    }

    @Override
    public Client update(Client entity) {
        clientRedisRepository.update(entity);
        return clientMongoRepository.update(entity);
    }

    @Override
    public long size() {
        return findAll().size();
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        if (clientRedisRepository.checkConnection()) {
            List<Client> found = clientRedisRepository.findAll();
            clients.addAll(found);
        } else {
            List<Client> found = clientMongoRepository.findAll();
            clients.addAll(found);
        }
        return clients;
    }
}
