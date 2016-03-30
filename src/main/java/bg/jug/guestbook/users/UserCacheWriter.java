package bg.jug.guestbook.users;

import bg.jug.guestbook.cache.JPA;
import bg.jug.guestbook.entities.User;
import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

import javax.cache.Cache;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;

/**
 * @author Ivan St. Ivanov
 */
@ApplicationScoped
public class UserCacheWriter implements CacheWriter<String, PayaraValueHolder> {

    @Inject
    @JPA
    private UserManager userManager;


    @Override
    public void write(Cache.Entry<? extends String, ? extends PayaraValueHolder> entry) throws CacheWriterException {
        try {
            userManager.addUser((User) entry.getValue().getValue());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeAll(Collection<Cache.Entry<? extends String, ? extends PayaraValueHolder>> entries) throws CacheWriterException {
        entries.forEach(this::write);
    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        // Do nothing. We don't want to remove a user when she is removed from cache.
    }

    @Override
    public void deleteAll(Collection<?> keys) throws CacheWriterException {
        // Do nothing. We don't want to remove a user when she is removed from cache.
    }
}
