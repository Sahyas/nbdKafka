package com.nbd.repository.impl;

import com.nbd.model.Client;
import com.nbd.repository.api.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {
    private final List<Client> clients;

    public ClientRepositoryImpl() {
        clients = new ArrayList<>();
    }

    @Override
    public List<Client> fetchClients() {
        return clients;
    }

    @Override
    public Optional<Client> findById(int id) {
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findFirst();
    }

    @Override
    public boolean addClient(Client client) {
        return clients.add(client);
    }

    @Override
    public boolean addClients(List<Client> clients) {
        return clients.addAll(clients);
    }

    @Override
    public boolean dropClient(Client client) {
        return clients.removeIf($client -> $client.getId() == client.getId());
    }

    @Override
    public boolean updateClient(Client client) {
        Optional<Client> foundClient = findById(client.getId());
        if (!foundClient.isPresent()) {
            return false;
        }
        dropClient(foundClient.get());
        return addClient(client);
    }
}
