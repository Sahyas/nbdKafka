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
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "rent_books",
            joinColumns = {@JoinColumn(name = "rent_id", referencedColumnName = "rent_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
    @NotNull
    private List<Book> books;
    @ManyToOne
    @JoinColumn
    private Client client;

    public Rent(List<Book> books, Client client) {
        this.beginTime = new Date();
        this.books = books;
        this.client = client;
    }
}
