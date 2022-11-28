package com.nbd.repository;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.nbd.model.Book;
import org.bson.conversions.Bson;

import javax.print.Doc;

public class BookRepositoryImpl extends AbstractMongoRepository {

    public BookRepositoryImpl() {
        super("books", Book.class);
    }

    public Book findBySerialNumber(String serialNumber) {
        MongoCollection<Book> collection = mongoDb.getCollection(collectionString, Book.class);
        Bson filter = Filters.eq("serialNumber", serialNumber);
        return collection
                .find()
                .filter(filter)
                .first();
    }

    public void clearDatabase() {
        MongoCollection<Book> collection = mongoDb.getCollection(collectionString, Book.class);
        collection.drop();
    }

    @Override
    public void close() throws Exception {
        System.out.println("closing book repository");
    }

    public void updateBook(Book book) {
        MongoCollection<Book> collection = mongoDb.getCollection(collectionString, Book.class);
        Bson filter = Filters.eq("_id", book.getId());
        Bson update = Updates.combine(
                Updates.set("title", book.getTitle()),
                Updates.set("author", book.getAuthor()),
                Updates.set("serialNumber", book.getSerialNumber()),
                Updates.set("genre", book.getGenre()),
                Updates.set("rented", book.isRented())
        );
        try {
            UpdateResult result = collection.updateOne(filter, update);
            System.out.println("Licznik modyfikacji dokumentu:" + result.getModifiedCount());
        } catch (MongoException e) {
            System.err.println("Cannot update because of: " + e.getMessage());
        }
    }
}
