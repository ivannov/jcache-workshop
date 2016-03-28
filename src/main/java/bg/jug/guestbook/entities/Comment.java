package bg.jug.guestbook.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "getAllComments", query = "SELECT c FROM Comment c")
})
public class Comment implements Serializable {

    private static final long serialVersionUID = -8061042352342915142L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private int version;

    @Size(max = 100)
	private String title;

	@Column(length = 3000)
    @Lob
	private String content;

	@ManyToOne
	private User byUser;

    public Comment() {
    }

    public Comment(String title, String content, User byUser) {
        this.title = title;
        this.content = content;
        this.byUser = byUser;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    public User getByUser() {
        return this.byUser;
    }

    public void setByUser(final User byUser) {
        this.byUser = byUser;
    }

    @Override public String toString() {
        return "Comment{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", byUser=" + byUser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment that = (Comment) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(byUser, that.byUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, byUser);
    }

}