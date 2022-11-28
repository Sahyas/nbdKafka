package com.nbd.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

@Getter
@Setter
@BsonDiscriminator(key = "_clazz")
@EqualsAndHashCode
public abstract class Client extends AbstractEntityMgd {
    @BsonProperty("firstName")
    private String firstName;
    @BsonProperty("lastName")
    private String lastName;
    @BsonProperty("personalId")
    private String personalId;
    @BsonProperty("archive")
    boolean isArchive;
    @BsonProperty("debt")
    private float debt;
    @BsonProperty("age")
    private int age;

    @BsonCreator
    public Client(@BsonProperty("_id") UUID entityId,
                  @BsonProperty("firstName") String firstName,
                  @BsonProperty("lastName") String lastName,
                  @BsonProperty("personalId") String personalId,
                  @BsonProperty("archive") boolean isArchive,
                  @BsonProperty("debt") float debt,
                  @BsonProperty("age") int age) {
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

