package com.nbd.repository.api.client;

import com.nbd.service.api.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<ClientModel> fetchClients();

    Optional<ClientModel> findById(int id);

    boolean addClient(ClientModel client);

    boolean addClients(List<ClientModel> clients);

    boolean dropClient(ClientModel client);

    boolean updateClient(ClientModel client);
}
