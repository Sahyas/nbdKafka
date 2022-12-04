package com.nbd.repository.mongo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.nbd.model.mongo.BookMgd;

import org.bson.conversions.Bson;

public class BookMongoRepository extends AbstractMongoRepository<BookMgd> {

    public BookMongoRepository() {
        super("books", BookMgd.class);
    }

    public BookMgd findBySerialNumber(String serialNumber) {
        MongoCollection<BookMgd> collection = mongoDb.getCollection(collectionString, BookMgd.class);
        Bson filter = Filters.eq("serialNumber", serialNumber);
        return collection
                .find()
                .filter(filter)
                .first();
    }

    public void clearDatabase() {
        MongoCollection<BookMgd> collection = mongoDb.getCollection(collectionString, BookMgd.class);
        collection.drop();
    }

    public void updateBook(BookMgd book) {
        MongoCollection<BookMgd> collection = mongoDb.getCollection(collectionString, BookMgd.class);
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
