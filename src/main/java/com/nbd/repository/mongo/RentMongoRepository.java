package com.nbd.repository.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.nbd.model.mongo.BookMgd;
import com.nbd.model.mongo.ClientMgd;
import com.nbd.model.mongo.RentMgd;

import org.bson.conversions.Bson;

public class RentMongoRepository extends AbstractMongoRepository<RentMgd> {
    public RentMongoRepository() {
        super("rents", RentMgd.class);
    }

    public void clearDatabase() {
        MongoCollection<RentMgd> collection = mongoDb.getCollection(collectionString, RentMgd.class);
        collection.drop();
    }

    public RentMgd findByBook(BookMgd book) {
        MongoCollection<RentMgd> collection = mongoDb.getCollection(collectionString, RentMgd.class);
        Bson filter = Filters.eq("book._id", book.getId());
        return collection
                .find()
                .filter(filter)
                .first();
    }

    public RentMgd findByClient(ClientMgd client) {
        MongoCollection<RentMgd> collection = mongoDb.getCollection(collectionString, RentMgd.class);
        Bson filter = Filters.eq("client._id", client.getId());
        return collection
                .find()
                .filter(filter)
                .first();
    }
}
