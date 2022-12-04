package com.nbd.repository.redis;

import com.nbd.model.redis.AbstractEntityRd;
import com.nbd.repository.RepositoryInterface;

import java.util.*;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

public class AbstractRedisRepository<T extends AbstractEntityRd> implements RepositoryInterface<T> {

    private static JedisPooled pool;
    private static Jsonb jsonb = JsonbBuilder.create();
    protected Class<T> clazz;
    private String prefix;

    public void initConnection() {
        JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().build();
        pool = new JedisPooled(new HostAndPort("localhost", 6379), clientConfig);
    }

    public AbstractRedisRepository(Class<T> clazz, String prefix) {
        this.clazz = clazz;
        this.prefix = prefix;
        initConnection();
    }

    public boolean checkConnection() {
        return pool.getPool().getResource().isConnected();
    }

    public void clearCache(){
        Set<String> keys = pool.keys("*");
        for (String key : keys){
            pool.del(key);
        }
    }

    public void clearThis(){
        Set<String> keys = pool.keys(prefix + "*");
        for (String key : keys){
            pool.del(key);
        }
    }

    public T getById(UUID id) {
        Optional<String> found = Optional.of(pool.get(prefix + id.toString()));
        return jsonb.fromJson(found.get(), this.clazz);
    }

    @Override
    public void delete(UUID id) {
        pool.del(prefix + getById(id));
    }

    public T add(T entity) {
        String entityStr = jsonb.toJson(entity);
        pool.set(prefix + entity.getId().toString(), entityStr);

        return entity;
    }

    public void remove(T entity) {
        pool.del(prefix + entity.getId().toString());
    }

    public T update(T updatedEntity) {
        String entityStr = jsonb.toJson(updatedEntity);
        pool.set(prefix + updatedEntity.getId().toString(), entityStr);

        return updatedEntity;
    }

    public long size() {
        return findAll().size();
    }

    public List<T> findAll() {
        List<T> all = new ArrayList<>();
        Set<String> keys = pool.keys(prefix + "*");
        for (String key : keys){
            all.add(jsonb.fromJson(pool.get(key), this.clazz));
        }

        return all;
    }

    @Override
    public T findByPersonalID(String personalId) {
        return null;
    }

    public void close() throws Exception {
        pool.getPool().destroy();
        pool.close();
    }
}
