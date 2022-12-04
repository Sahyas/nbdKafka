package com.nbd.model.redis;

import java.io.Serializable;
import java.util.UUID;

import javax.json.bind.annotation.JsonbProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AbstractEntityRd implements Serializable {
    @JsonbProperty("_id")
    protected UUID id;

    public AbstractEntityRd(UUID id) {
        this.id = id;
    }
}
