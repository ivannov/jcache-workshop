package bg.jug.guestbook.users;

import javax.enterprise.inject.Model;
import javax.validation.constraints.AssertTrue;
import javax.ws.rs.FormParam;

@Model
public class UserModel {

    @FormParam("userName")
    private String userName;

    @FormParam("password")
    private String password;

    @FormParam("reenterPassword")
    private String reenterPassword;

    @FormParam("firstName")
    private String firstName;

    @FormParam("lastName")
    private String lastName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReenterPassword() {
        return reenterPassword;
    }

    public void setReenterPassword(String reenterPassword) {
        this.reenterPassword = reenterPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @AssertTrue(message = "Passwords don't match")
    private boolean isValid() {
        return this.password.equals(this.reenterPassword);
    }
}
