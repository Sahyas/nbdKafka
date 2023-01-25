package com.nbd.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@BsonDiscriminator(key = "_clazz", value = "child")
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@JsonTypeName("child")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Child.class, name = "child")
})
public class Child extends Client {
    @JsonCreator
    @BsonCreator
    public Child(@BsonProperty("firstName") String firstName,
                 @BsonProperty("lastName") String lastName,
                 @BsonProperty("personalId") String personalID,
                 @BsonProperty("age") int age) {
        super(firstName, lastName, personalID, age);
    }

    @Override
    public int getMaxDays() {
        return 2160;
    }

    @Override
    public int getMaxBooks() {
        return 3;
    }

    @Override
    public String getTypeInfo() {
        return "CHILD";
    }

    @Override
    public float getPenalty() {
        return 5* 1.2f;
    }
}
