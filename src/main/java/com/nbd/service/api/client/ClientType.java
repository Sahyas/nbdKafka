package com.nbd.service.api.client;

public abstract class ClientType {
    public abstract int getMaxBooks();

    public abstract String getTypeInfo();

    public abstract int getMaxDays();

    public float getPenalty() {
        return 0;
    }
}
