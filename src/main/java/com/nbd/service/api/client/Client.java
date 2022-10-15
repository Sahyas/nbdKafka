package com.nbd.service.api.client;

import com.nbd.service.api.client.ClientType;

public class Client {
    private final String firstName;
    private final String lastName;
    private final String personalID;
    private final ClientType clientType;
    private int age;
    boolean isArchive = false;
    private float debt = 0;

    public Client(String firstName, String lastName, String personalID, int age, ClientType clientType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
        this.age = age;
        this.clientType = clientType;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalID() {
        return personalID;
    }

    public int getAge() {
        return age;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }
}

