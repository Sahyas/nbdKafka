package com.nbd.model.mongo;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@BsonDiscriminator(key = "_clazz", value = "adult")
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class AdultMgd extends ClientMgd {
    @BsonCreator
    public AdultMgd(@BsonProperty("firstName") String firstName,
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
