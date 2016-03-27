package bg.jug.website.comment;

import bg.jug.website.entities.Comment;
import bg.jug.website.entities.User;
import bg.jug.website.qualifiers.JPA;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Ivan St. Ivanov
 */
@RequestScoped
@JPA
public class JpaCommentsManager implements CommentsManager {

    @Inject
    private EntityManager em;

    public List<Comment> getAllComments() {
        return em.createNamedQuery("getAllComments", Comment.class).getResultList();
    }

    @Transactional
    public Comment submitComment(Comment newComment) {
        em.persist(newComment);
        return newComment;
    }

    @Transactional
    public void deleteCommentWithId(Long commentId) {
        final Comment comment = em.find(Comment.class, commentId);
        if (comment != null) {
            em.remove(comment);
        }
    }
}
