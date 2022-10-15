package com.nbd.service.impl.client;

import com.nbd.service.api.client.Client;
import com.nbd.service.api.client.ClientService;

import java.util.Optional;
import java.util.UUID;

public class ClientServiceImpl implements ClientService {

    @Override
    public Optional<Client> getClient(Client client) {
        return null;
    }

    @Override
    public Optional<Client> getClientById(UUID id) {
        return Optional.empty();
    }

    @Override
    public boolean addClient(Client client) {
        return false;
    }

    @Override
    public boolean deleteClient(UUID id) {
        return false;
    }

    @Override
    public String getInfo() {
        return null;
    }
}
