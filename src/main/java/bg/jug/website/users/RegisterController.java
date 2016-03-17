package bg.jug.website.users;

import bg.jug.website.entities.User;

import javax.inject.Inject;
import javax.mvc.annotation.Controller;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Controller
@Path("/register")
public class RegisterController {

    @GET
    public String showRegistrationForm() {
        return "register.jsp";
    }

    @Inject
    private UserManager userManager;

    @Inject
    private UserContext userContext;

    @POST
    public String registerUser(@Valid @BeanParam UserModel user) {
        User newUser = new User(user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName());
        userManager.addUser(newUser);
        userContext.setCurrentUser(newUser);
        return "redirect:/comment";
    }
}
