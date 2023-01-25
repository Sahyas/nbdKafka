package com.nbd.model;

import com.fasterxml.jackson.annotation.*;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@BsonDiscriminator(key = "_clazz", value = "adult")
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@JsonTypeName("adult")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Adult.class, name = "adult")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Adult extends Client {

    @BsonCreator
    public Adult(@BsonProperty("firstName") String firstName,
                 @BsonProperty("lastName") String lastName,
                 @BsonProperty("personalId") String personalID,
                 @BsonProperty("age") int age) {
        super(firstName, lastName, personalID, age);
    }
    @JsonCreator
    public Adult(@JsonProperty("_id") UUID uuid,
                 @JsonProperty("firstname") String firstName,
                 @JsonProperty("lastname") String lastName,
                 @JsonProperty("personalid") String personalID,
                 @JsonProperty("isarchived") boolean isArchived,
                 @JsonProperty("debt") Float debt,
                 @JsonProperty("age") Integer age) {
        super(uuid,firstName, lastName, personalID, isArchived, debt, age);
    }

    @Override
    public int getMaxDays() {
        return 1440;
    }

    @Override
    public int getMaxBooks() {
        return 5;
    }

    @Override
    public float getPenalty() {
        return 5*2.0f;
    }

    @Override
    public String getTypeInfo() {
        return "ADULT";
    }
}
