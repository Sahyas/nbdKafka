package com.nbd.model;

import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@BsonDiscriminator(key = "_clazz", value = "adult")
public class Adult extends Client {
    @BsonCreator
    public Adult(@BsonProperty("firstName") String firstName,
                 @BsonProperty("lastName") String lastName,
                 @BsonProperty("personalId") String personalID,
                 @BsonProperty("age") int age) {
        super(firstName, lastName, personalID, age);
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
