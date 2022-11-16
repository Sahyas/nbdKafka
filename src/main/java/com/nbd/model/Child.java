package com.nbd.model;

import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@BsonDiscriminator(key = "_clazz", value = "child")
public class Child extends Client {
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
