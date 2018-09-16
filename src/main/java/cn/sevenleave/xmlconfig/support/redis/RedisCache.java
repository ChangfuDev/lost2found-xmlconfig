package cn.sevenleave.xmlconfig.support.redis;

/**
 * 描述：redis缓存类,可以使用注解来使用缓存
 *
 * @author SevenLeave
 * @date 2018-09-01
 */

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class RedisCache implements Cache {

    private String name;
    private RedisTemplate<String, Object> redisTemplate;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void clear() {
        System.out.println("------緩存清理------");
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    @Override
    public void evict(Object key) {
        final String keyf = key.toString();
        System.out.println("------緩存刪除------// key:" + keyf);
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyf.getBytes());
            }
        });
    }

    @Override
    public ValueWrapper get(Object key) {
        System.out.println("------缓存获取------// key:" + key.toString());
        final String keyf = key.toString();
        Object object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    System.out.println("------缓存不存在------");
                    return null;
                }
                return SerializationUtils.deserialize(value);
            }
        });
        ValueWrapper obj = (object != null ? new SimpleValueWrapper(object) : null);
        System.out.println("------获取到缓存内容------// value:" + obj);
        return obj;
    }

    @Override
    public void put(Object key, Object value) {
        System.out.println("------加入缓存------// key:" + key.toString() + ", value:" + value);
        final String keyString = key.toString();
        final Object valueObj = value;
        // 设置缓存过期时间,单位是秒
        final long liveTime = 2 * 60;
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                // 序列化key-value
                byte[] keyBytes = keyString.getBytes();
                byte[] valueBytes = SerializationUtils.serialize((Serializable) valueObj);
                connection.set(keyBytes, valueBytes);
                if (liveTime > 0) {
                    connection.expire(keyBytes, liveTime);
                }
                return 1L;
            }
        });
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper putIfAbsent(Object arg0, Object arg1) {
        return null;
    }

}
