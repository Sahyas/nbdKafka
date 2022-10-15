package com.nbd.service.api;

import com.nbd.model.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Optional<Client> getClient(Client client);

    Optional<Client> getClientById(UUID id);

    boolean addClient(Client client);

    boolean deleteClient(UUID id);

    String getInfo();
}
