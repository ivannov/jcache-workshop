package bg.jug.website.comment;

import bg.jug.website.entities.Comment;
import bg.jug.website.entities.User;
import bg.jug.website.users.LoggedIn;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.stream.Collectors;

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

    @Inject
    private BindingResult br;

    @GET
    public String showAllComments() {
        models.put("comments", commentsManager.getAllComments());
        return "comments.jsp";
    }

    @GET
    @Path("/new")
    public String showNewCommentForm() {
        return "newComment.jsp";
    }

    @Inject
    private MessagesBean messagesBean;

    @GET
    @Path("/currentUser")
    public String showAllCommentsFromCurrentUser() {
        models.put("comments", commentsManager.getSessionsForUser(currentUser));
        return "comments.jsp";
    }

    @POST
    @ValidateOnExecution(type = ExecutableType.NONE)
    public Response submitSessionProposal(@Valid @BeanParam CommentModel submission) {
        if (br.isFailed()) {
            String errorMessage = br.getAllViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("<br>"));
            messagesBean.setMessage(errorMessage);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("newComment.jsp").build();
        }
        Comment newComment = new Comment(submission.getTitle(), submission.getContent(), currentUser);
        commentsManager.submitComment(newComment);
        return Response.seeOther(URI.create("comment")).build();
    }

}
