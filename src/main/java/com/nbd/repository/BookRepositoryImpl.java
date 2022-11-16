package com.nbd.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.bulk.UpdateRequest;
import com.nbd.model.Book;
import com.nbd.model.Client;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.EncoderContext;
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
        System.out.println("BookRepository close");
    }

    public void updateBook(Book book) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction();
            MongoCollection<Book> collection = mongoDb.getCollection(collectionString, Book.class);
            Bson filter = Filters.eq("id", book.getId());
            Bson update = Updates.combine(
                    Updates.set("title", book.getTitle()),
                    Updates.set("author", book.getAuthor()),
                    Updates.set("serialNumber", book.getSerialNumber()),
                    Updates.set("genre", book.getGenre()),
                    Updates.set("rented", book.isRented())
            );
            UpdateResult result = collection.updateOne(clientSession, filter, update);
            System.out.println(result.getMatchedCount());
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
        } finally {
            clientSession.close();
        }
        System.out.println(getById(book.getId()));
    }
}
