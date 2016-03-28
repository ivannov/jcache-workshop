package bg.jug.guestbook.users;

import bg.jug.guestbook.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * @author Ivan St. Ivanov`
 */
public class UserManager {

    @Inject
    private EntityManager em;

    public User getUser(String userName, String password) {
        TypedQuery<User> query = em.createNamedQuery("findUserByNameAndPassword", User.class);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Transactional
    public void addUser(User newUser) {
        em.persist(newUser);
    }
}
