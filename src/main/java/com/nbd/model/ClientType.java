package com.nbd.model;

public abstract class ClientType extends AbstractEntity{
    public abstract int getMaxBooks();

    public abstract String getTypeInfo();

    public abstract int getMaxDays();

    public float getPenalty() {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }
}
