package com.nbd.model;

import com.nbd.conventer.ClientTypeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String personalID;
    @Convert(converter = ClientTypeConverter.class)
    private ClientType clientType;
    private int age;
    boolean isArchive = false;
    private float debt = 0;

    public Client(final String firstName, final String lastName, final String personalID, final int age, final ClientType clientType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
        this.age = age;
        this.clientType = clientType;
    }
}

