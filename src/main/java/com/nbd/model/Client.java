package com.nbd.model;

import com.nbd.conventer.ClientTypeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "client")
@Access(AccessType.FIELD)
@Embeddable
public class Client extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Column
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String personalID;
    @Convert(converter = ClientTypeConverter.class)
    private ClientType clientType;
    @NotEmpty
    @Column
    private int age;
    @NotEmpty
    @Column
    boolean isArchive = false;
    @Column
    private float debt = 0;
    @OneToMany(mappedBy = "client")
    List<Rent> rents = new ArrayList<>();

    public Client(final String firstName, final String lastName, final String personalID, final int age, final ClientType clientType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
        this.age = age;
        this.clientType = clientType;
    }
}

