package net.avidreder.lead_measure;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import net.avidreder.lead_measure.domain.*;
import net.avidreder.lead_measure.domain.impl.DomainDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SpringBootApplication
public class Application {

    // Declare dependencies
    public static DomainDao domainDao;
    public static Session session;

    // public static UserDao userDao;

    public static void main(String[] args) {

//         Instantiate your dependencies
        domainDao = new DomainDaoImpl();
        SessionFactory sessionFactory = new Configuration()
                .setProperty("hibernate.connection.username", System.getenv("POSTGRES_USER"))
                .setProperty("hibernate.connection.password", System.getenv("POSTGRES_PASSWORD"))
                .configure()
                .buildSessionFactory();
        session = sessionFactory.openSession();
        SpringApplication.run(Application.class, args);
        // Configure Spark
//        port(4567);
//        staticFiles.location("/public");
//        staticFiles.expireTime(600L);
//        enableDebugScreen();
//
//        // Set up before-filters (called before each get/post)
//        // before("*",                  Filters.addTrailingSlashes);
//        // before("*",                  Filters.handleLocaleChange);
//
//        // Set up routes
//        get("/domains", DomainController.getAllDomains);
//        post("/domains", DomainController.createNewDomain);
//        get("/hello", (req, res) -> "Hello world");
//        get("/healthCheck", (req, res) -> "I am a health check Yayyy");
//        notFound((req, res) -> {
//            res.status(404);
//            return "Not Found";
//        });
//
//        //Set up after-filters (called after each get/post)
//        after("*",(req, res) -> res.type("application/json"));
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
