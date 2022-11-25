package com.nbd.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Client extends AbstractEntityMgd {
    private String firstName;
    private String lastName;
    private String personalId;
    boolean isArchive;
    private float debt;
    private int age;

    public Client(UUID entityId) {
        super(entityId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.age = age;
        this.debt = debt;
        this.isArchive = isArchive;
    }

    public Client(String firstName, String lastName, String personalId, int age) {
        super(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.age = age;
        this.debt = 0f;
        this.isArchive = false;
    }

    public abstract int getMaxDays();

    public abstract int getMaxBooks();

    public abstract String getTypeInfo();

    public abstract float getPenalty();
}

