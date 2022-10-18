package com.nbd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "book")
@Access(AccessType.FIELD)
@ToString
public class Book extends AbstractEntity {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    @NotNull
    private String title;
    @Column(name = "author")
    @NotNull
    private String author;
    @Column(name = "serialNumber")
    @NotNull
    private String serialNumber;
    @Column(name = "genre")
    private String genre;
    @Column
    boolean IsArchive = false;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn
    private Rent rent;

    public Book() {
    }

    public Book(String title, String author, String serialNumber, String genre) {
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
    }
}
