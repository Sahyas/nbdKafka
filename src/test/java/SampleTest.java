import com.nbd.model.Adult;
import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;
import com.nbd.repository.BookRepositoryImpl;
import com.nbd.repository.ClientRepositoryImpl;
import com.nbd.repository.RentRepositoryImpl;
import com.nbd.service.BookServiceImpl;
import com.nbd.service.ClientServiceImpl;
import com.nbd.service.RentServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private RentRepositoryImpl rentRepository;
    private BookRepositoryImpl bookRepository;
    private ClientRepositoryImpl clientRepository;

    private BookServiceImpl bookService;
    private RentServiceImpl rentService;
    private ClientServiceImpl clientService;

    @BeforeEach
    void beforeEach() {
        if (emf != null) {
            emf.close();
        }
        emf = Persistence.createEntityManagerFactory("POSTGRES");
        em = emf.createEntityManager();
        this.bookRepository = new BookRepositoryImpl(em);
        this.clientRepository = new ClientRepositoryImpl(em);
        this.rentRepository = new RentRepositoryImpl(em);
        this.rentService = new RentServiceImpl(rentRepository, clientRepository, bookRepository);
        this.clientService = new ClientServiceImpl(clientRepository);
        this.bookService = new BookServiceImpl(bookRepository);

    }

    @Test
    public void bookRepositoryTest() {
        Book book = new Book("abc", "def", "123", "fantasy");
        Book retrievedBook = bookService.registerBook("abc", "def", "123", "fantasy");
        assertThat(book.getTitle()).isEqualTo(retrievedBook.getTitle());
        bookService.unregisterBook(retrievedBook);
        retrievedBook = bookService.getBookById(retrievedBook.getId());
        assertThat(retrievedBook).isNull();
    }

    @Test
    public void clientRepositoryTest() {
        Client client = new Client("Szymon", "Zakrzewski", "1234", 21, new Adult());
        clientService.addClient(client);
        Client foundClient = clientService.getClientById(client.getId());
        assertThat(foundClient.getPersonalID()).isEqualTo(client.getPersonalID());
        clientService.deleteClient(client);
        assertThat(clientService.getClientById(foundClient.getId())).isNull();
    }

    @Test
    public void rentTest() {
        Client client = new Client("Szymon", "Zakrzewski", "1234", 21, new Adult());
        Book sampleBook = new Book("abc", "def", "123", "fantasy");
        Book sampleBook2 = new Book("abc1", "def1", "1234", "fantasy1");
        List<Book> bookList = Arrays.asList(sampleBook, sampleBook2);
        Rent rent = new Rent();
        rent.setBooks(bookList);
        clientService.addClient(client);
        bookService.registerBook("abc", "def", "123", "fantasy");
        bookService.registerBook("abc1", "def1", "1234", "fantasy1");
        rentService.rentBook(client, bookList);
        Client client2 = new Client("Szymon2", "Zakrzewski2", "12342", 212, new Adult());
        clientService.addClient(client2);
        rentService.rentBook(client2, bookList);
    }
}
