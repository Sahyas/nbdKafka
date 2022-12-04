package com.nbd.service;

import com.nbd.model.dto.Adult;
import com.nbd.model.dto.Child;
import com.nbd.model.dto.Client;
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
        return clientRepository.getById(id);
    }

    public Client getClientByPersonalId(String personalId) {
        return clientRepository.findByPersonalID(personalId);
    }

    public Client addClient(String firstName, String lastName, String personalId, int age) {
        if (age < 18) {
            return clientRepository.add(new Child(UUID.randomUUID(), firstName, lastName, personalId, age));
        }
        if (age > 18) {
            return clientRepository.add(new Adult(UUID.randomUUID(), firstName, lastName, personalId, age));
        }
        return null;
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client.getUniqueId());
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
}
