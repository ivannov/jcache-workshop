package bg.jug.guestbook.comment;

import bg.jug.guestbook.entities.Comment;
import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import java.io.IOException;

@ApplicationScoped
public class CommentAuthorEntryProcessor implements
		EntryProcessor<Long, PayaraValueHolder, Comment> {

	@Override
	public Comment process(MutableEntry<Long, PayaraValueHolder> entry,
			Object... arguments) throws EntryProcessorException {

		try {
			Comment comment = (Comment) entry.getValue().getValue();
			comment.setContent(comment.getContent() + " ["
					+ comment.getByUser().getUserName() + "]");
			entry.setValue(new PayaraValueHolder(comment));
			return comment;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
        return null;
	}

}
