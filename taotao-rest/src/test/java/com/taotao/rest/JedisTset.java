package com.taotao.rest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * @Author: 黄运锐
 * @Date: 18-4-29 上午10:13
 * @Description:
 */
public class JedisTset {

    @Test
    public void testJedisSingle(){
        //创建jedis对象
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //调用jedis对象的方法，方法名称和redis一样
        jedis.set("key1","jedis test");
        String key = jedis.get("key1");
        System.out.println(key);
        jedis.close();
    }

    /*
    * 使用连接池
    * */
    @Test
    public void testJedisPool(){
        //创建连接池
        JedisPool pool = new JedisPool("127.0.0.1",6379);
        //从连接池获得jedis对象
        Jedis jedis = pool.getResource();
//        jedis.hset("hello","key","100");
        System.out.println(jedis.hget("hello","key"));
//        jedis.hdel("hello","key1");

        jedis.close();
        pool.close();
    }

    /**
     * 集群版测试
     */
    @Test
    public void testJedisCluster(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        //集群列表
        nodes.add(new HostAndPort("127.0.0.1",7001));
        nodes.add(new HostAndPort("127.0.0.1",7002));
        nodes.add(new HostAndPort("127.0.0.1",7003));
        nodes.add(new HostAndPort("127.0.0.1",7004));
        nodes.add(new HostAndPort("127.0.0.1",7005));
        nodes.add(new HostAndPort("127.0.0.1",7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("key1","1000");
        String str = cluster.get("key1");
        System.out.println(str);

        cluster.close();
    }

    @Test
    public void testSpringJedisSingle(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisPool pool = (JedisPool) context.getBean("redisClient");
        Jedis jedis = pool.getResource();
        jedis.set("key","value");
        String value = jedis.get("key");
        System.out.println(value);
        jedis.close();
        pool.close();
    }

    @Test
    public void testSpringJedisCluster(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisCluster cluster = (JedisCluster) context.getBean("redisClient");
        cluster.set("key","value");
        String value = cluster.get("key");
        System.out.println(value);
        cluster.close();
    }

}
