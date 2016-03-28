package bg.jug.guestbook.comment;

import bg.jug.guestbook.entities.Comment;
import bg.jug.guestbook.qualifiers.JCache;

import javax.enterprise.context.RequestScoped;
import java.util.List;

/**
 * @author Ivan St. Ivanov
 */
@RequestScoped
@JCache
public class JCacheCommentsManager implements CommentsManager {

    @Override
    public List<Comment> getAllComments() {
        return null;
    }

    @Override
    public Comment submitComment(Comment newComment) {
        return null;
    }

    @Override
    public void deleteCommentWithId(Long commentId) {
    }
}
