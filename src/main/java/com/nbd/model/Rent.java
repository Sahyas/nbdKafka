package com.nbd.model;

import com.nbd.model.Client;
import com.nbd.model.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Table(name = "rents")
@Access(AccessType.FIELD)
@Entity
@ToString
@NoArgsConstructor
public class Rent extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private int id;

    private Date beginTime;
    private Date endTime;
    private Book book;
    private Client client;

    public Rent(int id, Book book, Client client) {
        this.id = id;
        this.book = book;
        this.client = client;
        beginTime = new Date();
    }
}
