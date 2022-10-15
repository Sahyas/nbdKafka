package com.nbd.repository.impl.client;

import com.nbd.repository.api.book.BookModel;
import com.nbd.repository.api.client.ClientModel;
import com.nbd.repository.api.client.ClientRepository;
import com.nbd.repository.impl.book.BookRepositoryImpl;
import com.nbd.service.api.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {
    private final List<ClientModel> clients;

    public ClientRepositoryImpl() {
        clients = new ArrayList<>();
    }

    @Override
    public List<ClientModel> fetchClients() {
        return clients;
    }

    @Override
    public Optional<ClientModel> findById(int id) {
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findFirst();
    }

    @Override
    public boolean addClient(ClientModel client) {
        return clients.add(client);
    }

    @Override
    public boolean addClients(List<ClientModel> clients) {
        return clients.addAll(clients);
    }

    @Override
    public boolean dropClient(ClientModel client) {
        return clients.removeIf($client -> $client.getId() == client.getId());
    }

    @Override
    public boolean updateClient(ClientModel client) {
        Optional<ClientModel> foundClient = findById(client.getId());
        if (!foundClient.isPresent()) {
            return false;
        }
        dropClient(foundClient.get());
        return addClient(client);
    }
}
