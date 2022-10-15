package com.nbd.repository.api;

import com.nbd.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> fetchClients();

    Optional<Client> findById(int id);

    boolean addClient(Client client);

    boolean addClients(List<Client> clients);

    boolean dropClient(Client client);

    boolean updateClient(Client client);
}
