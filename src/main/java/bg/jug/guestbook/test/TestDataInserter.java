package bg.jug.guestbook.test;

import bg.jug.guestbook.entities.Comment;
import bg.jug.guestbook.entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ivan St. Ivanov
 */
@Singleton
@Startup
public class TestDataInserter {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void insertTestData() {
        em.createQuery("DELETE FROM Comment").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();

        User mitya = new User("mitya", "mitya", "Dmitriy", "Alexandrov");
        User misho = new User("misho", "misho", "Mihail", "Stoynov");
        User nayden = new User("nayden", "nayden", "Nayden", "Gochev", true);
        User martin = new User("martin", "martin", "Martin", "Toshev");

        Comment myfear = new Comment("@myfear",
                "Great! Great! GREAT!! @myfear will speak at @jPrimeConf 2016!!!",
                mitya);
        Comment photo = new Comment("Photo",
                "\"Never a straight face :(\" (my mom when i was a kid) ",
                misho);
        Comment vars = new Comment("var in Java",
                "I dont think you will be able to write var someList = new ArrayList<>() maybe = new ArrayList<ClassName>() still.. I am guessing Java 11 :)",
                nayden);
        Comment freePasses = new Comment("Free passes",
                "A JUG lead has the chance to attend jPrime 2016 with a free pass",
                martin);
        Comment top10 = new Comment("Top 10 mistakes",
                "Java Beginner level but good to check : Top 10 Most Common Mistakes That Java Developers Make",
                nayden);

        mitya.getComments().add(myfear);
        misho.getComments().add(photo);
        nayden.getComments().add(vars);
        martin.getComments().add(freePasses);
        nayden.getComments().add(top10);

        em.persist(mitya);
        em.persist(misho);
        em.persist(nayden);
        em.persist(martin);
    }

}
