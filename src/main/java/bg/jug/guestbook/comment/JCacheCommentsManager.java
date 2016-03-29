package bg.jug.guestbook.comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache;
import javax.cache.configuration.CompleteConfiguration;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import bg.jug.guestbook.entities.Comment;
import bg.jug.guestbook.cache.JCache;
import bg.jug.guestbook.cache.JPA;
import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

/**
 * @author Ivan St. Ivanov
 */
@ApplicationScoped
@JCache
public class JCacheCommentsManager implements CommentsManager {

	@Inject
	@JPA
	private CommentsManager passThroughCommentsManager;

    @Inject
	private CommentAuthorEntryProcessor processor;

	@Inject
	private Cache<Long, PayaraValueHolder> cache;

	@Override
	public List<Comment> getAllComments() throws ClassNotFoundException,
			IOException {

		Iterator<Cache.Entry<Long, PayaraValueHolder>> commentsCacheIterator = cache
				.iterator();
		if (commentsCacheIterator.hasNext()) {
			// Converting iterator to Stream is a bit ugly, so doing it the
			// Java 7 way
			List<Comment> foundComments = new ArrayList<>();
			while (commentsCacheIterator.hasNext()) {
				Comment comment = (Comment) commentsCacheIterator.next()
						.getValue().getValue();
				foundComments.add(comment);
			}
			return foundComments;
		}

		List<Comment> dbComments = passThroughCommentsManager.getAllComments();
		
		dbComments.forEach(comment -> {
			try {
				cache.put(comment.getId(), new PayaraValueHolder(comment));
				cache.invoke(comment.getId(), processor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return dbComments;

	}
	
	@Override
	public Comment submitComment(Comment newComment) throws IOException {
		Comment submittedComment = passThroughCommentsManager
				.submitComment(newComment);
		cache.put(submittedComment.getId(), new PayaraValueHolder(
				submittedComment));
		cache.invoke(submittedComment.getId(), processor);
		return submittedComment;
	}

	@Override
	public void deleteCommentWithId(Long commentId) {
		passThroughCommentsManager.deleteCommentWithId(commentId);
		cache.remove(commentId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getStatistics() {
		return cache.getConfiguration(CompleteConfiguration.class);
	}
}
