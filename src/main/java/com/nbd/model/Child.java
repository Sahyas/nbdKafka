package com.nbd.model;

import lombok.Getter;

@Getter
public class Child extends Client {

    public Child(String firstName, String lastName, String personalId, int age) {
        super(firstName, lastName, personalId, age);
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
