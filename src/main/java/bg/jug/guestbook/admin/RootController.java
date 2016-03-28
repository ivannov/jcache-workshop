package bg.jug.guestbook.admin;

import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Ivan St. Ivanov
 */
@Controller
@Path("/")
public class RootController {

    @GET
    public String homePage() {
        return "redirect:/comment";
    }
}
