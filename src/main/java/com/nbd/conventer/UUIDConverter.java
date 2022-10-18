package com.nbd.conventer;

import jakarta.persistence.AttributeConverter;

import java.util.UUID;

public class UUIDConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return null;
    }

    @Override
    public UUID convertToEntityAttribute(String s) {
        return null;
    }
}
