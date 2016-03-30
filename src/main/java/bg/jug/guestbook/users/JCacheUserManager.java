package bg.jug.guestbook.users;

import bg.jug.guestbook.cache.JCache;
import bg.jug.guestbook.entities.User;
import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

import javax.cache.Cache;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;

/**
 * @author Ivan St. Ivanov
 */
@JCache
@RequestScoped
public class JCacheUserManager implements UserManager {

    @Inject
    private Cache<String, PayaraValueHolder> userCache;

    @Override
    public User getUser(String userName, String password) {
        PayaraValueHolder valueHolder = userCache.get(userName);
        if (valueHolder != null) {
            try {
                User user = (User) valueHolder.getValue();
                if (user.getPassword().equals(password)) {
                    return user;
                } else {
                    return null;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    @Override
    public void addUser(User newUser) {
        try {
            userCache.putIfAbsent(newUser.getUserName(), new PayaraValueHolder(newUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByName(String userName) {
        PayaraValueHolder payaraValueHolder = userCache.get(userName);
        try {
            return (User) payaraValueHolder.getValue();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
