package com.nbd.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class AbstractEntityMgd implements Serializable {
    @BsonProperty("_id")
    @BsonId
    protected UUID id;

    public AbstractEntityMgd(UUID id) {
        this.id = id;
    }
}

