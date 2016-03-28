package bg.jug.guestbook.comment;

import bg.jug.guestbook.entities.Comment;
import bg.jug.guestbook.qualifiers.JCache;
import bg.jug.guestbook.qualifiers.JPA;

import javax.cache.Cache;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ivan St. Ivanov
 */
@RequestScoped
@JCache
public class JCacheCommentsManager implements CommentsManager {

    @Inject
    @JPA
    private CommentsManager passThroughCommentsManager;

    @Inject
    private Cache<Long, Comment> cache;

    @Override
    public List<Comment> getAllComments() {
        Iterator<Cache.Entry<Long, Comment>> commentsCacheIterator = cache.iterator();
        if (commentsCacheIterator.hasNext()) {
            // Converting iterator to Stream is a bit ugly, so doing it the Java 7 way
            List<Comment> foundComments = new ArrayList<>();
            while (commentsCacheIterator.hasNext()) {
                foundComments.add(commentsCacheIterator.next().getValue());
            }
            return foundComments;
        }

        List<Comment> dbComments = passThroughCommentsManager.getAllComments();
        dbComments.forEach(comment -> cache.put(comment.getId(), comment));
        return dbComments;
    }

    @Override
    public Comment submitComment(Comment newComment) {
        Comment submittedComment = passThroughCommentsManager.submitComment(newComment);
        cache.put(submittedComment.getId(), submittedComment);
        return submittedComment;
    }

    @Override
    public void deleteCommentWithId(Long commentId) {
        passThroughCommentsManager.deleteCommentWithId(commentId);
        cache.remove(commentId);
    }
}
