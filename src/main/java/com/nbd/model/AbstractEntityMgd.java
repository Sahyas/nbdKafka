package com.nbd.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class AbstractEntityMgd implements Serializable {
    protected UUID id;

    public AbstractEntityMgd(UUID id) {
        this.id = id;
    }
}

