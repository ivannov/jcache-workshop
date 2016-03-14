package bg.jug.website.comment;

import bg.jug.website.entities.Comment;
import bg.jug.website.entities.User;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Ivan St. Ivanov
 */
@RequestScoped
public class CommentsManager {

    @Inject
    private EntityManager em;

    public List<Comment> getAllComments() {
        return em.createNamedQuery("getAllSessions", Comment.class).getResultList();
    }

    public List<Comment> getSessionsForUser(User user) {
        TypedQuery<Comment> query = em.createNamedQuery("findSessionsByUser", Comment.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Transactional
    public Comment submitComment(Comment newSession) {
        em.persist(newSession);
        return newSession;
    }
}
