package bg.jug.guestbook.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
		@NamedQuery(name = "findUserByNameAndPassword", query = "SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password")
})
@Table(name = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = -1774213830687034509L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private int version;

    @NotNull
	private String userName;

    @NotNull
	private String password;

	private String firstName;

	private String lastName;

	private boolean isAdmin;

	@OneToMany(mappedBy = "byUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

    public User() {
    }

    public User(String userName, String password, String firstName, String lastName) {
        this(userName, password, firstName, lastName, false);
    }

    public User(String userName, String password, String firstName, String lastName,
            boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> submissions) {
        this.comments = submissions;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
	public String toString() {
		return "User{" + "userName='" + userName + '\'' + ", firstName='"
				+ firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(userName, user.userName)
				&& Objects.equals(password, user.password)
				&& Objects.equals(firstName, user.firstName)
				&& Objects.equals(lastName, user.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, password, firstName, lastName);
	}

}