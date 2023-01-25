package com.nbd.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

import jakarta.json.bind.annotation.JsonbSubtype;
import jakarta.json.bind.annotation.JsonbTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_clazz")
@JsonbTypeInfo({
        @JsonbSubtype(alias = "child", type = Child.class),
        @JsonbSubtype(alias = "adult", type = Adult.class)
})
@Getter
@Setter
@BsonDiscriminator(key = "_clazz")
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
public abstract class Client extends AbstractEntity {

    @JsonProperty("firstName")
    @BsonProperty("firstName")
    private String firstName;
    @BsonProperty("lastName")
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("personalId")
    @BsonProperty("personalId")
    private String personalId;
    @JsonProperty("archive")
    @BsonProperty("archive")
    boolean isArchive;
    @JsonProperty("debt")
    @BsonProperty("debt")
    private float debt;
    @JsonProperty("age")
    @BsonProperty("age")
    private int age;

    @JsonCreator
    @BsonCreator
    public Client(@BsonProperty("_id") UUID id,
                  @BsonProperty("firstName") String firstName,
                  @BsonProperty("lastName") String lastName,
                  @BsonProperty("personalId") String personalId,
                  @BsonProperty("archive") boolean isArchive,
                  @BsonProperty("debt") float debt,
                  @BsonProperty("age") int age) {
        super(id);
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

