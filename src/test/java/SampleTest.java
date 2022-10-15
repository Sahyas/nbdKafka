import com.nbd.model.Adult;
import com.nbd.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SampleTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("POSTGRES");
        em = emf.createEntityManager();
    }

    @AfterAll
    static void afterAll() {
       // if (emf != null) {
       //     emf.close();
       // }
    }

    @Test
    public void clientTest() {
        Client client = new Client("Szymon", "Zakrzewski", "1234", 21, new Adult());
    }
}
