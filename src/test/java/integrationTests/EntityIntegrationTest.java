package integrationTests;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import net.avidreder.lead_measure.entity.Measure;
import net.avidreder.lead_measure.domain.Domain;

public class EntityIntegrationTest {
    private static Session session;
    private static Domain testDomain = new Domain("myDomain");

    @BeforeClass
    public static void setupTests() {
        SessionFactory sessionFactory = new Configuration()
                .setProperty("hibernate.connection.username", System.getenv("POSTGRES_USER"))
                .setProperty("hibernate.connection.password", System.getenv("POSTGRES_PASSWORD"))
                .configure()
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }
    @Test
    public void createTestDomain() {

        session.beginTransaction();

        Domain newDomain = new Domain("myDomain");
        session.save(newDomain);
        session.getTransaction().commit();
    }

    @Test
    public void createTestMeasure() {
        session.beginTransaction();

        Measure newMeasure = new Measure("myMeasure", testDomain );
        session.save(newMeasure);
        session.getTransaction().commit();
    }

    @AfterClass
    public static void tearDownTests() {
        session.close();
    }
}
