package com.bvvy.redis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by bvvy on 2017/7/3.
 */
public class TestRedis {

    public static void main(String[] args) {
        JedisConnectionFactory jcf = new JedisConnectionFactory();
        jcf.setPort(6379);
        RedisTemplate<String,String>  rt =new RedisTemplate<>();
        rt.setConnectionFactory(jcf);

    }
}
