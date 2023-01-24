package com.nbd.model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public abstract class AbstractEntity implements Serializable {
    @BsonProperty("_id")
    @BsonId
    protected UUID id;

    @BsonCreator
    public AbstractEntity(@BsonProperty("_id") UUID uniqueId) {
        this.id = uniqueId;
    }
}

