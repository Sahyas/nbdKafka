package com.nbd.service;

import com.nbd.model.Adult;
import com.nbd.model.Child;
import com.nbd.model.Client;
import com.nbd.repository.ClientRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class ClientServiceImpl {
    private final ClientRepositoryImpl clientRepository;

    public ClientServiceImpl() {
        this.clientRepository = new ClientRepositoryImpl();
    }

    public Object getClientById(UUID id) {
        return null;
    }

    public Client getClientByPersonalId(String personalId) {
        return clientRepository.findByPersonalID(personalId);
    }

    public void addClient(String firstName, String lastName, String personalId, int age) {
        if (age < 18) {
            clientRepository.add(new Child(firstName, lastName, personalId, age));
        }
        if (age > 18) {
            clientRepository.add(new Adult(firstName, lastName, personalId, age));
        }
    }

    public void deleteClient(Client client) {

    }

    public List<Client> findAllClients() {
        return null;
    }
}
