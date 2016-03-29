package bg.jug.guestbook.comment;

import bg.jug.guestbook.entities.Comment;
import bg.jug.guestbook.entities.User;
import bg.jug.guestbook.qualifiers.JCache;
import bg.jug.guestbook.users.LoggedIn;

import javax.inject.Inject;
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

import java.io.IOException;
import java.net.URI;
import java.util.stream.Collectors;

/**
 * @author Ivan St. Ivanov
 */
@Controller
@Path("/newcomment")
public class NewCommentController {

    @Inject
    private BindingResult br;

    @Inject
    private MessagesBean messagesBean;

    @Inject
    @JCache
    private CommentsManager commentsManager;

    @Inject
    @LoggedIn
    private User currentUser;

    @GET
    public String showNewCommentForm() {
        return "newComment.jsp";
    }

    @POST
    @ValidateOnExecution(type = ExecutableType.NONE)
    public Response submitComment(@Valid @BeanParam CommentModel comment) throws IOException {
        if (br.isFailed()) {
            String errorMessage = br.getAllViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("<br>"));
            messagesBean.setMessage(errorMessage);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("newComment.jsp").build();
        }
        Comment newComment = new Comment(comment.getTitle(), comment.getContent(), currentUser);
        commentsManager.submitComment(newComment);
        return Response.seeOther(URI.create("comment")).build();
    }


}
