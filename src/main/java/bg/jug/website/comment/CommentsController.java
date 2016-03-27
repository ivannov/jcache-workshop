package bg.jug.website.comment;

import bg.jug.website.entities.Comment;
import bg.jug.website.entities.User;
import bg.jug.website.qualifiers.JPA;
import bg.jug.website.users.LoggedIn;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
        return prepareModelAndView(commentsManager.getAllComments());
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

    @POST
    @Path("/search")
    public String filterComments(@FormParam("searchTerm") String searchTerm) {
        return prepareModelAndView(commentsManager.getAllComments()
                .stream()
                .filter(comment -> commentSatisfiesTerm(comment, searchTerm))
                .collect(Collectors.toList()));
    }

    private String prepareModelAndView(List<Comment> comments) {
        models.put("comments", comments);
        models.put("user", currentUser);
        return "comments.jsp";
    }

    private boolean commentSatisfiesTerm(Comment comment, String searchTerm) {
        return comment.getTitle().contains(searchTerm) ||
                comment.getContent().contains(searchTerm);
    }
}
