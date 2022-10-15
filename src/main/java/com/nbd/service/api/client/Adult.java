package com.nbd.service.api.client;

public class Adult extends ClientType {
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
        return super.getPenalty()*2.0f;
    }

    @Override
    public String getTypeInfo() {
        return "ADULT";
    }
}
