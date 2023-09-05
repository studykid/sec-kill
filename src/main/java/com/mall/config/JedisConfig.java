package com.mall.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * @author wy
 */
@Configuration
public class JedisConfig {

    private Logger logger= LoggerFactory.getLogger(JedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        JedisPool jedisPool=new JedisPool(jedisPoolConfig,host,port);
        JedisPool jedisPool1=new JedisPool(jedisPoolConfig,host,port);
        logger.info("JedisPool连接成功："+host+":"+port);
        return jedisPool;

    }

    @Bean
    public JedisCluster jedisCluster() {
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxTotal(1);{}
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("10.3.0.4", 20447));
        nodes.add(new HostAndPort("10.3.0.3", 20449));
        nodes.add(new HostAndPort("10.3.0.2", 20448));
        return new JedisCluster(nodes);
    }
}
