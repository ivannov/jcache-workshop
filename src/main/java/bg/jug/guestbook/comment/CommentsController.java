package bg.jug.guestbook.comment;

import bg.jug.guestbook.entities.User;
import bg.jug.guestbook.qualifiers.JPA;
import bg.jug.guestbook.users.LoggedIn;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * @author Ivan St. Ivanov
 */
@Controller
@Path("/comment")
public class CommentsController {

    @Inject
    @JPA
    private CommentsManager commentsManager;

    @Inject
    private MessagesBean messagesBean;

    @Inject
    @LoggedIn
    private User currentUser;

    @Inject
    private Models models;

    @GET
    public String showAllComments() {
        models.put("comments", commentsManager.getAllComments());
        models.put("user", currentUser);
        return "comments.jsp";
    }

    @GET
    @Path("/delete")
    public Response deleteComment(@QueryParam("commentId") Long commentId) {
        if (currentUser.isAdmin()) {
            commentsManager.deleteCommentWithId(commentId);
        } else {
            messagesBean.setMessage("Only admin users are allowed to delete comments");
        }
        return Response.seeOther(URI.create("comment")).build();
    }
}
