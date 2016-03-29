package bg.jug.guestbook.users;

import bg.jug.guestbook.entities.User;
import bg.jug.guestbook.qualifiers.JCache;

/**
 * @author Ivan St. Ivanov
 */
@JCache
public class JCacheUserManager implements UserManager {

    @Override
    public User getUser(String userName, String password) {
        return null;
    }

    @Override
    public void addUser(User newUser) {

    }

    @Override
    public User findUserByName(String userName) {
        return null;
    }
}
