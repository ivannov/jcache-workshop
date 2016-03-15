package bg.jug.website.comment;

import bg.jug.website.entities.User;
import bg.jug.website.users.LoggedIn;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Ivan St. Ivanov
 */
@Controller
@Path("/comment")
public class CommentsController {

    @Inject
    private CommentsManager commentsManager;

    @Inject
    @LoggedIn
    private User currentUser;

    @Inject
    private Models models;

    @GET
    public String showAllComments() {
        models.put("comments", commentsManager.getAllComments());
        return "comments.jsp";
    }

    @GET
    @Path("/currentUser")
    public String showAllCommentsFromCurrentUser() {
        models.put("comments", commentsManager.getSessionsForUser(currentUser));
        return "comments.jsp";
    }

}
