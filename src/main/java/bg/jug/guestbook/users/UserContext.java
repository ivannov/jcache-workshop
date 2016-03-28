package bg.jug.guestbook.users;

import bg.jug.guestbook.entities.User;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;

/**
 * @author Ivan St. Ivanov
 */
@SessionScoped
public class UserContext implements Serializable {

    private static final long serialVersionUID = -7093851207387888951L;

    private User currentUser;

    @Produces
    @LoggedIn
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
