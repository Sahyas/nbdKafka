package com.nbd.repository.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.nbd.model.mongo.AbstractEntityMgd;
import com.nbd.model.mongo.AdultMgd;
import com.nbd.model.mongo.ChildMgd;
import com.nbd.model.mongo.ClientMgd;
import com.nbd.repository.RepositoryInterface;

import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class AbstractMongoRepository<T extends AbstractEntityMgd> implements RepositoryInterface<T> {

    protected ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
    protected MongoCredential credential = MongoCredential.createCredential("nbd", "admin", "nbdpassword".toCharArray());
    protected CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
            .automatic(true)
            .register(ClientMgd.class, AdultMgd.class, ChildMgd.class)
            .conventions(Conventions.DEFAULT_CONVENTIONS)
            .build());
    protected MongoClient mongoClient;

    protected MongoDatabase mongoDb;

    protected final String collectionString;

    private final Class<T> entityClassName;

    public AbstractMongoRepository(String collection, Class<T> entityClassName) {
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

    @Override
    public T add(T entity) {
        MongoCollection<T> collection = mongoDb.getCollection(collectionString, entityClassName);
        collection.insertOne(entity);
        return entity;
    }

    @Override
    public void delete(UUID id) {
        MongoCollection<T> collection = mongoDb.getCollection(collectionString, entityClassName);
        Bson query = eq("_id", id);
        collection.deleteOne(query);
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public long size() {
        return findAll().size();
    }

    @Override
    public T getById(UUID id) {
        MongoCollection<T> collection = mongoDb.getCollection(collectionString, entityClassName);
        return collection.find(eq("_id", id)).first();
    }

    @Override
    public List<T> findAll() {
        List<T> objects = new ArrayList<>();
        MongoCollection<T> collection = mongoDb.getCollection(collectionString, entityClassName);
        MongoCursor<T> cursor = collection.find().cursor();
        while (cursor.hasNext()) {
            objects.add(cursor.next());
        }
        return objects;
    }
    @Override
    public T findByPersonalID(String personalId) {
        MongoCollection<T> collection = mongoDb.getCollection(collectionString, entityClassName);
        Bson filter = Filters.eq("personalId", personalId);
        return collection
                .find()
                .filter(filter)
                .first();
    }

    public void clearDatabase() {
        MongoCollection<ClientMgd> collection = mongoDb.getCollection(collectionString, ClientMgd.class);
        collection.drop();
    }
}
