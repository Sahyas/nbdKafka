package com.nbd.model.mapper;

import com.nbd.model.dto.Adult;
import com.nbd.model.dto.Child;
import com.nbd.model.dto.Client;
import com.nbd.model.mongo.AdultMgd;
import com.nbd.model.mongo.ChildMgd;
import com.nbd.model.mongo.ClientMgd;
import com.nbd.model.redis.AdultRd;
import com.nbd.model.redis.ChildRd;
import com.nbd.model.redis.ClientRd;

public class ClientMapper {
    public static ClientMgd toBsonDocument(Client client) {
        if (client instanceof Child) {
            return childToBsonDocument(client);
        }
        if (client instanceof Adult) {
            return adultToBsonDocument(client);
        }
        return null;
    }

    public static ClientRd toJsonDocument(Client client) {
        if(client instanceof Child) {
            return childToJsonDocument(client);
        }
        if(client instanceof Adult) {
            return adultToJsonDocument(client);
        }
        return null;
    }
    public static ClientMgd childToBsonDocument(Client client) {
        return ChildMgd.builder()
            .age(client.getAge())
            .debt(client.getDebt())
            .firstName(client.getFirstName())
            .isArchive(client.isArchive())
            .personalId(client.getPersonalId())
            .lastName(client.getLastName())
            .id(client.getUniqueId())
            .build();
    }

    public static ClientRd childToJsonDocument(Client client) {
        return ChildRd.builder()
            .age(client.getAge())
            .debt(client.getDebt())
            .firstName(client.getFirstName())
            .isArchive(client.isArchive())
            .personalId(client.getPersonalId())
            .lastName(client.getLastName())
            .id(client.getUniqueId())
            .build();
    }

    public static ClientMgd adultToBsonDocument(Client client) {
        return AdultMgd.builder()
                .age(client.getAge())
                .debt(client.getDebt())
                .firstName(client.getFirstName())
                .isArchive(client.isArchive())
                .personalId(client.getPersonalId())
                .lastName(client.getLastName())
                .id(client.getUniqueId())
                .build();
    }

    public static ClientRd adultToJsonDocument(Client client) {
        return AdultRd.builder()
                .age(client.getAge())
                .debt(client.getDebt())
                .firstName(client.getFirstName())
                .isArchive(client.isArchive())
                .personalId(client.getPersonalId())
                .lastName(client.getLastName())
                .id(client.getUniqueId())
                .build();
    }

    public static Child childRdToChild(ChildRd childRd) {
        return Child.builder()
                .age(childRd.getAge())
                .debt(childRd.getDebt())
                .firstName(childRd.getFirstName())
                .isArchive(childRd.isArchive())
                .personalId(childRd.getPersonalId())
                .lastName(childRd.getLastName())
                .build();
    }

    public static Child childMgdToChild(ChildMgd childMgd) {
        return Child.builder()
                .age(childMgd.getAge())
                .debt(childMgd.getDebt())
                .firstName(childMgd.getFirstName())
                .isArchive(childMgd.isArchive())
                .personalId(childMgd.getPersonalId())
                .lastName(childMgd.getLastName())
                .build();
    }

    public static Client clientMgdToClient(ClientMgd clientMgd) {
        return Client.builder()
                .age(clientMgd.getAge())
                .debt(clientMgd.getDebt())
                .firstName(clientMgd.getFirstName())
                .isArchive(clientMgd.isArchive())
                .personalId(clientMgd.getPersonalId())
                .lastName(clientMgd.getLastName())
                .uniqueId(clientMgd.getId())
                .build();
    }

}
