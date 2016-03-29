package bg.jug.guestbook.comment;

import java.io.IOException;
import java.io.Serializable;

import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;

import bg.jug.guestbook.entities.Comment;
import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

public class CommentAuthorEntryProcessor implements
		EntryProcessor<Long, PayaraValueHolder, Comment>, Serializable {

	private static final long serialVersionUID = 5257349479693469233L;

	@Override
	public Comment process(MutableEntry<Long, PayaraValueHolder> entry,
			Object... arguments) throws EntryProcessorException {

		try {
			Comment comment = (Comment) entry.getValue().getValue();
			comment.setContent(comment.getContent() + " ["
					+ comment.getByUser().getUserName() + "]");
			entry.setValue(new PayaraValueHolder(comment));
			return comment;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
