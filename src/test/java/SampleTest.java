import com.nbd.model.Adult;
import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void rentTest() {
        Client client = new Client("Szymon", "Zakrzewski", "1234", 21, new Adult());
        Book sampleBook = new Book("abc", "def", "123", "fantasy");
        Book sampleBook2 = new Book("abc1", "def1", "1234", "fantasy1");
        List<Book> bookList = Arrays.asList(sampleBook, sampleBook2);
        Rent rent = new Rent();
        rent.setBook(bookList);

    }
}
