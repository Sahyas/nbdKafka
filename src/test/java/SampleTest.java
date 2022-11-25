import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;
import com.nbd.repository.BookRepositoryImpl;
import com.nbd.repository.ClientRepositoryImpl;
import com.nbd.repository.RentRepositoryImpl;
import com.nbd.service.BookServiceImpl;
import com.nbd.service.ClientServiceImpl;
import com.nbd.service.RentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest {
    private RentRepositoryImpl rentRepository;
    private BookRepositoryImpl bookRepository;
    private ClientRepositoryImpl clientRepository;

    private BookServiceImpl bookService;
    private RentServiceImpl rentService;
    private ClientServiceImpl clientService;

    @BeforeEach
    void beforeEach() {
        this.bookRepository = new BookRepositoryImpl();
        this.rentRepository = new RentRepositoryImpl();
        this.clientRepository = new ClientRepositoryImpl();

        this.clientService = new ClientServiceImpl();
        this.bookService = new BookServiceImpl();
        this.rentService = new RentServiceImpl();
    }

    @Test
    void simpleBookRepositoryTest() {

    }

    @Test
    void simpleClientRepositoryTest() {

    }

    @Test
    void simpleRentRepositoryTest() {

    }

    @Test
    void rentRentedBookTest() {

    }

    @Test
    void returnBookTest() {

    }
}
