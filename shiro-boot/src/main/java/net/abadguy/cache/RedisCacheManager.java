package net.abadguy.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * 自定义shiro缓存管理器
 */
@Slf4j
public class RedisCacheManager implements CacheManager {


    /**
     *
     * @param cacheName  认证或者授权缓存的统一名字
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        log.info("cacheName=[{}]",cacheName);
        return new RedisCache<K,V>(cacheName);
    }
}
