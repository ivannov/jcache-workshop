package bg.jug.guestbook.users;

import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

import javax.cache.configuration.Factory;
import javax.cache.integration.CacheWriter;
import javax.inject.Inject;

/**
 * @author Ivan St. Ivanov
 */
public class UserCacheWriterFactory implements Factory<CacheWriter<String, PayaraValueHolder>> {

    @Inject
    private UserCacheWriter userCacheWriter;

    @Override
    public CacheWriter<String, PayaraValueHolder> create() {
        return userCacheWriter;
    }
}
