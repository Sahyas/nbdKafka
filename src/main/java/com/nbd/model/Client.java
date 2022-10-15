package com.nbd.model;

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

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("personalID", personalID)
                .append("age", age)
                .append("isArchive", isArchive)
                .append("debt", debt)
                .toString();
    }
}

