package bg.jug.guestbook.comment;

import bg.jug.guestbook.entities.Comment;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author Ivan St. Ivanov
 */
@ApplicationScoped
public class CommentsCacheProducer {

    private static final String COMMENTS_CACHE_NAME = "comments";

    @Inject
    private CacheManager cacheManager;

    @Produces
    @RequestScoped
    public Cache<Long, Comment> getCommentsCache() {
        Cache<Long, Comment> cache = cacheManager.getCache(COMMENTS_CACHE_NAME, Long.class, Comment.class);
        if (cache == null) {
            cache = cacheManager.createCache(COMMENTS_CACHE_NAME,
                    new MutableConfiguration<Long, Comment>().setTypes(Long.class, Comment.class));
        }
        return cache;
    }
}
