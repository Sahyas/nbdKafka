package com.nbd.service;

import com.nbd.model.Adult;
import com.nbd.model.Child;
import com.nbd.model.Client;
import com.nbd.repository.ClientRepository;
import com.nbd.repository.mongo.ClientMongoRepository;
import com.nbd.repository.redis.ClientRedisRepository;

import java.util.List;
import java.util.UUID;

public class ClientServiceImpl {
    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        this.clientRepository = new ClientRepository(new ClientRedisRepository(), new ClientMongoRepository());
    }

    public Client getClientById(UUID id) {
        return clientRepository.getById(id).orElse(null);
    }

    public Client addClient(String firstName, String lastName, String personalId, int age) {
        if (age < 18) {
            return clientRepository.add(new Child(firstName, lastName, personalId, age));
        }
        if (age > 18) {
            return clientRepository.add(new Adult(firstName, lastName, personalId, age));
        }
        return null;
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client.getId());
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
}
