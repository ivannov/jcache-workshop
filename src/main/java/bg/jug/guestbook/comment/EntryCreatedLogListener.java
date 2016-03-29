package bg.jug.guestbook.comment;

import java.util.logging.Logger;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;

import fish.payara.cdi.jsr107.impl.PayaraValueHolder;

public class EntryCreatedLogListener implements
		CacheEntryCreatedListener<Long, PayaraValueHolder> {

	private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

	int hits = 0;
	
	@Override
	public void onCreated(
			Iterable<CacheEntryEvent<? extends Long, ? extends PayaraValueHolder>> events)
			throws CacheEntryListenerException {
		for (CacheEntryEvent<? extends Long, ? extends PayaraValueHolder> event : events) {
			hits++;
			LOGGER.info("New entry value added in comments cache. Current additions: " + hits);
		}
	}
}
