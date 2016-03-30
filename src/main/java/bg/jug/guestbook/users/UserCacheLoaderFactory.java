package bg.jug.guestbook.users;

import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

import javax.cache.configuration.Factory;
import javax.cache.integration.CacheLoader;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Ivan St. Ivanov
 */
@ApplicationScoped
public class UserCacheLoaderFactory implements Factory<CacheLoader<String,PayaraValueHolder>> {

    @Inject
    private UserCacheLoader userCacheLoader;

    @Override
    public CacheLoader<String, PayaraValueHolder> create() {
        return userCacheLoader;
    }
}
