package com.nbd.repository;

import com.nbd.model.dto.Client;
import com.nbd.model.mapper.ClientMapper;
import com.nbd.model.mongo.ClientMgd;
import com.nbd.model.redis.ClientRd;
import com.nbd.repository.mongo.ClientMongoRepository;
import com.nbd.repository.redis.ClientRedisRepository;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ClientRepository implements RepositoryInterface<Client> {
    private ClientRedisRepository clientRedisRepository;
    private ClientMongoRepository clientMongoRepository;

    public ClientRepository(ClientRedisRepository clientRedisRepository, ClientMongoRepository clientMongoRepository) {
        this.clientRedisRepository = clientRedisRepository;
        this.clientMongoRepository = clientMongoRepository;
    }

    @Override
    public Client add(Client entity) {
         clientRedisRepository.add(ClientMapper.toJsonDocument(entity));
         return ClientMapper.clientMgdToClient(clientMongoRepository.add(ClientMapper.toBsonDocument(entity)));
    }

    @Override
    public Client getById(UUID id) {
        Client client = null;
        if (clientRedisRepository.checkConnection()) {
            client = ClientMapper.clientRdtoClient(clientRedisRepository.getById(id));
        }
        if (client == null) {
            return ClientMapper.clientMgdToClient(clientMongoRepository.getById(id));
        }
        return client;
    }

    @Override
    public void delete(UUID id) {
        clientRedisRepository.delete(id);
        clientMongoRepository.delete(id);
    }

    @Override
    public Client update(Client entity) {
        clientRedisRepository.update(ClientMapper.toJsonDocument(entity));
        return ClientMapper.clientMgdToClient(clientMongoRepository.update(ClientMapper.toBsonDocument(entity)));
    }

    @Override
    public long size() {
        return findAll().size();
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        if (clientRedisRepository.checkConnection()) {
            List<ClientRd> found = clientRedisRepository.findAll();
            for (ClientRd client: found) {
                clients.add(ClientMapper.clientRdtoClient(client));
            }
        } else {
            List<ClientMgd> found = clientMongoRepository.findAll();
            for (ClientMgd client: found) {
                clients.add(ClientMapper.clientMgdToClient(client));
            }
        }
        return clients;
    }

    @Override
    public Client findByPersonalID(String personalId) {
        throw new NotImplementedException();
    }
}
