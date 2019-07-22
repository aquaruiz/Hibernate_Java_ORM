package app.ccb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class CcbApplication {

    public static void main(String[] args) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ccb_db");
//        EntityManager em = factory.createEntityManager();

        SpringApplication.run(CcbApplication.class, args);
    }
}
