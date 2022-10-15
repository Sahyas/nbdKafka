package com.nbd.repository.api.client;

import com.nbd.service.api.client.ClientType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String personalID;
    private String clientType;
    private int age;
    boolean isArchive;
    private float debt;

    public int getId() {
        return id;
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

    public String getClientType() {
        return clientType;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }
}
