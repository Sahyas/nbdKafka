package com.nbd.repository;

import com.nbd.model.Client;
import redis.clients.jedis.*;
import redis.clients.jedis.JedisCluster.*;

import java.util.Set;

public abstract class RedisRepository<T> implements AutoCloseable {
    private JedisCluster cluster;

    public RedisRepository() {
        initDbConnection();
    }

    private void initDbConnection() {
        JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().password("master123").build();

        Set<HostAndPort> clusterUris = Set.of(
                new HostAndPort("localhost", 7001),
                new HostAndPort("localhost", 7002),
                new HostAndPort("localhost", 7003),
                new HostAndPort("localhost", 7004),
                new HostAndPort("localhost", 7005),
                new HostAndPort("localhost", 7006)
        );
        cluster = new JedisCluster(clusterUris, clientConfig);
        System.out.println("Connection to redis");
    }

    public void add(T entity) {
    }
}
