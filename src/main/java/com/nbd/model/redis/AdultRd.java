package com.nbd.model.redis;

import org.bson.codecs.pojo.annotations.BsonCreator;

import javax.json.bind.annotation.JsonbProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class AdultRd extends ClientRd {
    @BsonCreator
    public AdultRd(@JsonbProperty("firstName") String firstName,
                    @JsonbProperty("lastName") String lastName,
                    @JsonbProperty("personalId") String personalID,
                    @JsonbProperty("age") int age) {
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
