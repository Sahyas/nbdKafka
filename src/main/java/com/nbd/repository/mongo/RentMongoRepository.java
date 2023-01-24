package com.nbd.repository.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;

import org.bson.conversions.Bson;

public class RentMongoRepository extends AbstractMongoRepository<Rent> {
    public RentMongoRepository() {
        super("rents", Rent.class);
    }

    public void clearDatabase() {
        MongoCollection<Rent> collection = mongoDb.getCollection(collectionString, Rent.class);
        collection.drop();
    }

    public Rent findByBook(Book book) {
        MongoCollection<Rent> collection = mongoDb.getCollection(collectionString, Rent.class);
        Bson filter = Filters.eq("book._id", book.getId());
        return collection
                .find()
                .filter(filter)
                .first();
    }

    public Rent findByClient(Client client) {
        MongoCollection<Rent> collection = mongoDb.getCollection(collectionString, Rent.class);
        Bson filter = Filters.eq("client._id", client.getId());
        return collection
                .find()
                .filter(filter)
                .first();
    }
}
