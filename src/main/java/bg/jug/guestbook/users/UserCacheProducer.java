package bg.jug.guestbook.users;

import bg.jug.guestbook.cache.Hazelcast;
import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author Ivan St. Ivanov
 */
@ApplicationScoped
public class UserCacheProducer {

    private static final String USERS_CACHE_NAME = "users";

    @Inject
    @Hazelcast
    private CacheManager cacheManager;

    @Inject
    private UserCacheLoaderFactory loaderFactory;

    @Inject
    private UserCacheWriterFactory writerFactory;

    @Produces
    @RequestScoped
    public Cache<String, PayaraValueHolder> getUsersCache() {
        MutableConfiguration<String, PayaraValueHolder> cacheConfig = new MutableConfiguration<>();
        cacheConfig.setTypes(String.class, PayaraValueHolder.class)
                   .setReadThrough(true)
                   .setCacheLoaderFactory(loaderFactory)
                   .setWriteThrough(true)
                   .setCacheWriterFactory(writerFactory);
        return cacheManager.createCache(USERS_CACHE_NAME, cacheConfig);
    }
}
