package com.nbd.model;

import lombok.Getter;

@Getter
public class Adult extends Client {
    public Adult(String firstName, String lastName, String personalId, int age) {
        super(firstName, lastName, personalId, age);
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
