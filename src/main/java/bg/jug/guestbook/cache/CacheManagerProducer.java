package bg.jug.guestbook.cache;

import com.hazelcast.cache.impl.HazelcastServerCachingProvider;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;

import javax.cache.CacheManager;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

/**
 * @author Ivan St. Ivanov
 */
@ApplicationScoped
public class CacheManagerProducer {

    private HazelcastInstance hazelcastInstance;

    @Produces
    @Hazelcast
    public CacheManager getHazelcastCacheManager() {
        ClassLoader appClassLoader = CacheManagerProducer.class.getClassLoader();
        Config config = new Config();
        config.setClassLoader(appClassLoader);
        this.hazelcastInstance = com.hazelcast.core.Hazelcast.newHazelcastInstance(config);
        CachingProvider cp = HazelcastServerCachingProvider.createCachingProvider(hazelcastInstance);
        return cp.getCacheManager(cp.getDefaultURI(), appClassLoader);
    }

    public void shutdownHazelcastInstance(@Disposes @Hazelcast CacheManager cacheManager) {
        cacheManager.close();
        hazelcastInstance.shutdown();
    }
}
