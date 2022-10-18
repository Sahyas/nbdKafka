package com.nbd.repository;

import com.nbd.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class BookRepositoryImpl extends JpaRepositoryImpl<Book> {

    public BookRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("Select book from Book book", Book.class).getResultList();
    }

    @Override
    public Book getById(int id) {
        return em.find(Book.class, id);
    }
}
