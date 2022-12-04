package com.nbd.model.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public abstract class Client extends AbstractEntity {
    private String firstName;
    private String lastName;
    private String personalId;
    boolean isArchive;
    private float debt;
    private int age;

    public Client(UUID uniqueId, String firstName, String lastName, String personalId, int age) {
        super(uniqueId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.isArchive = false;
        this.debt = 0f;
        this.age = age;
    }
}
