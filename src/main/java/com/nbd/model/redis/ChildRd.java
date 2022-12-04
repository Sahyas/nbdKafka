package com.nbd.model.redis;

import javax.json.bind.annotation.JsonbProperty;

import jakarta.json.bind.annotation.JsonbCreator;
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
public class ChildRd extends ClientRd {
    @JsonbCreator
    public ChildRd(@JsonbProperty("firstName") String firstName,
                    @JsonbProperty("lastName") String lastName,
                    @JsonbProperty("personalId") String personalID,
                    @JsonbProperty("age") int age) {
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
