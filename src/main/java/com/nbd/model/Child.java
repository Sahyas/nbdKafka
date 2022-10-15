package com.nbd.model;

public class Child extends ClientType {
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
        return super.getPenalty() * 1.2f;
    }
}
