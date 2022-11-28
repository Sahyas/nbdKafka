package com.nbd.repository;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import com.nbd.model.Adult;
import com.nbd.model.Child;
import com.nbd.model.Client;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractMongoRepository<T> implements AutoCloseable {

    protected ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
    protected MongoCredential credential = MongoCredential.createCredential("nbd", "admin", "nbdpassword".toCharArray());
    protected CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
            .automatic(true)
            .register(Client.class, Adult.class, Child.class)
            .conventions(Conventions.DEFAULT_CONVENTIONS)
            .build());
    protected MongoClient mongoClient;

    protected MongoDatabase mongoDb;

    protected final String collectionString;

    private final Class<T> entityClassName;

    protected AbstractMongoRepository(String collection, Class<T> entityClassName) {
        this.collectionString = collection;
        this.entityClassName = entityClassName;
        initDbConnection();
    }

    private void initDbConnection() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .codecRegistry(CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry
                ))
                .build();

        mongoClient = MongoClients.create(settings);
        mongoDb = mongoClient.getDatabase("rentabook");
    }

    public void add(T entity) {
        MongoCollection<T> collection = mongoDb.getCollection(collectionString, entityClassName);
        collection.insertOne(entity);
    }

    public void delete(UUID id) {
        MongoCollection<T> collection = mongoDb.getCollection(collectionString, entityClassName);
        Bson query = eq("_id", id);
        collection.deleteOne(query);
    }

    public T getById(UUID id) {
        MongoCollection<T> collection = mongoDb.getCollection(collectionString, entityClassName);
        return collection.find(eq("_id", id)).first();
    }

    public List<T> findAll() {
        List<T> objects = new ArrayList<>();
        MongoCollection<T> collection = mongoDb.getCollection(collectionString, entityClassName);
        MongoCursor<T> cursor = collection.find().cursor();
        while (cursor.hasNext()) {
            objects.add(cursor.next());
        }
        return objects;
    }
}
