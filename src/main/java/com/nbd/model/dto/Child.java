package com.nbd.model.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Child extends Client {
    public Child(UUID uniqueId, String firstName, String lastName, String personalId, int age) {
        super(uniqueId, firstName, lastName, personalId, age);
    }
}
