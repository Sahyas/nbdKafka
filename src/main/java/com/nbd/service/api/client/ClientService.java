package com.nbd.service.api.client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Optional<Client> getClient(Client client);

    Optional<Client> getClientById(UUID id);

    boolean addClient(Client client);

    boolean deleteClient(UUID id);

    String getInfo();
}
