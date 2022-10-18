package com.nbd.service;

import com.nbd.model.Client;
import com.nbd.repository.ClientRepositoryImpl;

public class ClientServiceImpl {
    private final ClientRepositoryImpl clientRepository;

    public ClientServiceImpl(ClientRepositoryImpl clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(int id) {
        return clientRepository.getById(id);
    }

    public Client addClient(Client client) {
        return clientRepository.add(client);
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }
}
