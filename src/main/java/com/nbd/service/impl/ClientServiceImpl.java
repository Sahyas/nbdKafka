package com.nbd.service.impl;

import com.nbd.model.Client;
import com.nbd.service.api.ClientService;

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
