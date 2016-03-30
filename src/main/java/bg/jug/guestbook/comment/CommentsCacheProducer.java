package bg.jug.guestbook.comment;

import bg.jug.guestbook.cache.Hazelcast;
import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivan St. Ivanov
 */
@ApplicationScoped
public class CommentsCacheProducer {

	private static final String COMMENTS_CACHE_NAME = "comments";

    @Inject
    @Hazelcast
	private CacheManager cacheManager;
	
	@Produces
	@RequestScoped
	public Cache<Long, PayaraValueHolder> getCommentsCache() {
		
		Cache<Long, PayaraValueHolder> cache = cacheManager.getCache(COMMENTS_CACHE_NAME,
				Long.class, PayaraValueHolder.class);
		if (cache == null) {
			MutableConfiguration<Long, PayaraValueHolder> cacheConfig = new MutableConfiguration<>();
			cacheConfig.setTypes(Long.class, PayaraValueHolder.class);
			cacheConfig.setExpiryPolicyFactory(FactoryBuilder
					.factoryOf(new AccessedExpiryPolicy(new Duration(
							TimeUnit.MINUTES, 3))));
			cacheConfig
					.addCacheEntryListenerConfiguration(new MutableCacheEntryListenerConfiguration<>(
							FactoryBuilder.factoryOf(EntryCreatedLogListener.class),
							null, true, true));
			cache = cacheManager.createCache(COMMENTS_CACHE_NAME, cacheConfig);
		}
		return cache;
	}
}
