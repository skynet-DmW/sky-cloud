package org.sky.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Set;

/**
 * 通用的Redis工具类
 *
 * @author 赵明明
 */
@Service
public class RedisService {

    // 注入分片的连接池
    @Autowired
    private ShardedJedisPool pool;


    /**
     * 执行set命令，添加字符串格式数据
     *
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value) {
        return execute((ShardedJedis jedis) -> jedis.set(key, value));
    }


    /**
     * 执行set命令，添加字符串格式数据，并且设置存活时间,单位是秒
     *
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value, final Integer seconds) {
        return execute(new Function<String>() {
            @Override
            public String commond(ShardedJedis jedis) {
                String str = jedis.set(key, value);
                jedis.expire(key, seconds);
                return str;
            }
        });
    }


    /**
     * 执行get命令，查询字符串格式数据
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return execute((ShardedJedis jedis) -> jedis.get(key));
    }


    /**
     * 执行hset命令，添加字符串格式数据，并且设置存活时间,单位是秒
     *
     * @param key
     * @param value
     * @return
     */
    public Long hset(final String key, final String field, final String value, final Integer seconds) {
        return execute((ShardedJedis jedis) -> jedis.expire(key, seconds));
    }


    /**
     * 执行hget命令，查询字符串格式数据
     *
     * @param key
     * @return
     */
    public String hget(final String key, final String field) {
        return execute((ShardedJedis jedis) -> jedis.hget(key, field));
    }


    /**
     * 执行hget命令，查询字符串格式数据
     *
     * @param key
     * @return
     */
    public Set<String> hget(final String key) {
        return execute((ShardedJedis jedis) -> jedis.hkeys(key));
    }


    /**
     * 执行del命令，删除数据
     *
     * @param key
     * @return
     */
    public Long del(final String key) {
        return execute((ShardedJedis jedis) -> jedis.del(key));
    }


    /**
     * 执行expire命令，设置存活时间
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(final String key, final Integer seconds) {
        return execute((ShardedJedis jedis) -> jedis.expire(key, seconds));
    }


    /**
     * 抽取公用部分代码
     *
     * @param func
     * @param <R>
     * @return
     */
    public <R> R execute(Function<R> func) {
        ShardedJedis shardedJedis = null;
        try {
            // 从池中获取连接
            shardedJedis = pool.getResource();
            // 添加数据
            return func.commond(shardedJedis);
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
    }


    /**
     * 用接口封装要执行的语句，把接口的实现类对象作为参数传递，等同于传递一段代码
     *
     * @param <R>
     */
    private interface Function<R> {
        // 包含要执行语句的方法，因为返回值未知，所以用泛型
        R commond(ShardedJedis jedis);
    }
}
