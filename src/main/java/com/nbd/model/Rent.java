package com.nbd.model;

import com.nbd.model.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "Rent")
@Access(AccessType.FIELD)
@Entity
@ToString
@NoArgsConstructor
public class Rent extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private int id;
    @Column
    @NotNull
    private Date beginTime;
    @Column
    private Date endTime;
    @OneToMany(mappedBy = "rent")
    @NotNull
    private List<Book> book = new ArrayList<>();
    @ManyToOne
    @JoinColumn
    private Client client;
}
