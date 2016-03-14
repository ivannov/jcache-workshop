package bg.jug.website.comment;

import javax.enterprise.inject.Model;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * @author Ivan St. Ivanov
 */
@Model
public class CommentModel {

    @Size(max = 100)
    @FormParam("title")
    private String title;

    @FormParam("content")
    private String content;

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
}
