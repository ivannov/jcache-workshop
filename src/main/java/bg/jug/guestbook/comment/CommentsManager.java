package bg.jug.guestbook.comment;

import bg.jug.guestbook.entities.Comment;

import java.io.IOException;
import java.util.List;

/**
 * @author Ivan St. Ivanov
 */
interface CommentsManager {

    List<Comment> getAllComments() throws ClassNotFoundException, IOException;

    Comment submitComment(Comment newComment) throws IOException;
    
    void deleteCommentWithId(Long commentId);

	default Object getStatistics() {
		return new Object();
	}
}
