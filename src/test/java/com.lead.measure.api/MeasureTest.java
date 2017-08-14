package com.lead.measure.api;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lead.measure.api.entity.Measure;

public class MeasureTest {
    @Test
    public void createTestMeasure() {
      SessionFactory sessionFactory = new Configuration()
        .setProperty("hibernate.connection.username", System.getenv("POSTGRES_USER"))
        .setProperty("hibernate.connection.password", System.getenv("POSTGRES_PASSWORD"))
        .configure()
        .buildSessionFactory();
      Session session = sessionFactory.openSession();
	    session.beginTransaction();

      Measure newMeasure = new Measure("myMeasure");
      session.save(newMeasure);
      session.getTransaction().commit();
	    session.close();
      assertEquals(true, true);
    }
}
