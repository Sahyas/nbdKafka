package com.nbd.conventer;

import com.nbd.model.Adult;
import com.nbd.model.Child;
import com.nbd.model.ClientType;
import jakarta.persistence.AttributeConverter;

public class ClientTypeConverter implements AttributeConverter<ClientType, String> {
    @Override
    public String convertToDatabaseColumn(ClientType clientType) {
        return clientType.toString();
    }

    @Override
    public ClientType convertToEntityAttribute(String s) {
        return switch (s) {
            case "Child" -> new Child();
            case "Adult" -> new Adult();
            default -> throw new IllegalArgumentException();
        };
    }
}
