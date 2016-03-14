package bg.jug.website.test;

import bg.jug.website.entities.Comment;
import bg.jug.website.entities.User;

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

    public static User DEFAULT_USER;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void insertTestData() {
        em.createQuery("DELETE FROM Comment").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();

        User mitya = new User("mitya", "mitya", "Dmitriy", "Alexandrov");
        User vassil = new User("vassil", "vassil", "Vassil", "Dichev");
        User nayden = new User("nayden", "nayden", "Nayden", "Gochev");
        User martin = new User("martin", "martin", "Martin", "Toshev");

        DEFAULT_USER = nayden;

        Comment nashorn = new Comment("Nashorn: JavaScript runtime for JVM",
                "- Java(Script) � overview of Nashorn\n" +
                    "� Description of the IDE for developing Nashorn applications\n" +
                    "� Project Avatar",
                mitya);
        Comment scala = new Comment("Scala- one step ahead",
                "Did you know that the current javac compiler is written by the creator of the Scala language?",
                vassil);
        Comment springMvc = new Comment("Introduction to Spring & Spring MVC",
                "The lecture will explain the idea of Spring and IoC conrainers, then we will look at Spring MVC which " +
                        "is the most commonly used MVC framework in the Java space.",
                nayden);
        Comment activeMq = new Comment("The RabbitMQ message broker",
                "The session will provide an overview of the Rabbit messaging broker along with a demonstration on " +
                        "the various types of messaging patterns implemented in terms of the broker.",
                martin);

        Comment mvc10 = new Comment("JSR 371: MVC 1.0",
                "Hi friends! We kick off the 2015/2016 activities with a workshop on MVC 1.0. It is a new spec, that" +
                        " is coming in Java EE 8 and is being defined by JSR 371 so we will kick in the adopt a JSR " +
                        "initiative as well.",
                nayden);

        mitya.getComments().add(nashorn);
        vassil.getComments().add(scala);
        nayden.getComments().add(springMvc);
        martin.getComments().add(activeMq);
        nayden.getComments().add(mvc10);

        em.persist(mitya);
        em.persist(vassil);
        em.persist(nayden);
        em.persist(martin);
    }

}
