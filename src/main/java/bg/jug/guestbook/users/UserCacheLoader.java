package bg.jug.guestbook.users;

import bg.jug.guestbook.cache.JPA;
import bg.jug.guestbook.entities.User;
import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Ivan St. Ivanov
 */
@ApplicationScoped
public class UserCacheLoader implements CacheLoader<String, PayaraValueHolder> {

    @Inject
    @JPA
    private UserManager userManager;

    @Override
    public PayaraValueHolder load(String key) throws CacheLoaderException {
        try {
            return new PayaraValueHolder(userManager.findUserByName(key));
        } catch (IOException e) {
            throw new CacheLoaderException(e);
        }
    }

    @Override
    public Map<String, PayaraValueHolder> loadAll(Iterable<? extends String> keys) throws CacheLoaderException {
        return StreamSupport.stream(keys.spliterator(), false)
                            .collect(Collectors.toMap(Function.identity(),
                                     this::load));
    }
}
