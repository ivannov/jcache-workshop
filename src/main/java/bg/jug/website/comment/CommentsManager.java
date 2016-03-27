package bg.jug.website.comment;

import bg.jug.website.entities.Comment;

import java.util.List;

/**
 * @author Ivan St. Ivanov
 */
interface CommentsManager {

    List<Comment> getAllComments();

    Comment submitComment(Comment newComment);

    void deleteCommentWithId(Long commentId);
}
