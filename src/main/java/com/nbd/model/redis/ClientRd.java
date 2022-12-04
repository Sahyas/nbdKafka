package com.nbd.model.redis;

import java.util.UUID;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import jakarta.json.bind.annotation.JsonbSubtype;
import jakarta.json.bind.annotation.JsonbTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
@JsonbTypeInfo({
        @JsonbSubtype(alias = "child", type = ChildRd.class),
        @JsonbSubtype(alias = "adult", type = AdultRd.class)
})
public abstract class ClientRd extends AbstractEntityRd {
    @JsonbProperty("firstName")
    private String firstName;
    @JsonbProperty("lastName")
    private String lastName;
    @JsonbProperty("personalId")
    private String personalId;
    @JsonbProperty("archive")
    boolean isArchive;
    @JsonbProperty("debt")
    private float debt;
    @JsonbProperty("age")
    private int age;

    @JsonbCreator
    public ClientRd(@JsonbProperty("_id") UUID entityId,
                     @JsonbProperty("firstName") String firstName,
                     @JsonbProperty("lastName") String lastName,
                     @JsonbProperty("personalId") String personalId,
                     @JsonbProperty("archive") boolean isArchive,
                     @JsonbProperty("debt") float debt,
                     @JsonbProperty("age") int age) {
        super(entityId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.age = age;
        this.debt = debt;
        this.isArchive = isArchive;
    }

    public ClientRd(String firstName, String lastName, String personalId, int age) {
        super(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.age = age;
        this.debt = 0f;
        this.isArchive = false;
    }

    public abstract int getMaxDays();

    public abstract int getMaxBooks();

    public abstract String getTypeInfo();

    public abstract float getPenalty();
}

