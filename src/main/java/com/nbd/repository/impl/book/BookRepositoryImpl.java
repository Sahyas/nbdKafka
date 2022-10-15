package com.nbd.repository.impl.book;

import com.nbd.repository.api.book.BookModel;
import com.nbd.repository.api.book.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {
    private final List<BookModel> books;

    public BookRepositoryImpl() {
        this.books = new ArrayList<>();
    }

    @Override
    public List<BookModel> fetchBooks() {
        return books;
    }

    @Override
    public Optional<BookModel> findBySerialNumber(int id) {
        return books.stream()
                .filter(book -> book.getSerialNumber() == id)
                .findFirst();
    }

    @Override
    public boolean addBook(BookModel book) {
        return books.add(book);
    }

    @Override
    public boolean addBooks(List<BookModel> books) {
        return books.addAll(books);
    }

    @Override
    public boolean dropBook(BookModel book) {
        return books.remove(book);
    }

    @Override
    public boolean updateBook(BookModel book) {
        Optional<BookModel> foundBook = findBySerialNumber(book.getSerialNumber());
        if (!foundBook.isPresent()) {
            return false;
        }
        dropBook(foundBook.get());
        return addBook(book);
    }
}
