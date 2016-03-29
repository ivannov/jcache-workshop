package bg.jug.guestbook.users;

import bg.jug.guestbook.entities.User;
import bg.jug.guestbook.cache.JCache;
import bg.jug.guestbook.cache.JPA;
import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

import javax.cache.Cache;
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

    @Inject
    @JPA
    private UserManager passThroughManager;

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

        User dbUser = passThroughManager.getUser(userName, password);
        if (dbUser != null) {
            try {
                userCache.put(userName, new PayaraValueHolder(dbUser));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dbUser;
    }

    @Override
    public void addUser(User newUser) {
        passThroughManager.addUser(newUser);
        try {
            userCache.putIfAbsent(newUser.getUserName(), new PayaraValueHolder(newUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByName(String userName) {
        PayaraValueHolder payaraValueHolder = userCache.get(userName);
        User user = null;
        if (payaraValueHolder == null) {
            user = passThroughManager.findUserByName(userName);
            if (user != null) {
                try {
                    userCache.put(userName, new PayaraValueHolder(user));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                user = (User) payaraValueHolder.getValue();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
