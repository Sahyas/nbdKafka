import com.nbd.model.dto.Adult;
import com.nbd.model.dto.Client;
import com.nbd.model.mongo.BookMgd;
import com.nbd.model.mongo.RentMgd;
import com.nbd.model.redis.BookRd;
import com.nbd.repository.ClientRepository;
import com.nbd.repository.mongo.BookMongoRepository;
import com.nbd.repository.mongo.ClientMongoRepository;
import com.nbd.repository.mongo.RentMongoRepository;
import com.nbd.repository.redis.BookRedisRepository;
import com.nbd.repository.redis.ClientRedisRepository;
import com.nbd.repository.redis.RentRedisRepository;
import com.nbd.service.BookServiceImpl;
import com.nbd.service.ClientServiceImpl;
import com.nbd.service.RentServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest {
    private RentMongoRepository rentMongoRepository;
    private BookMongoRepository bookMongoRepository;
    private ClientMongoRepository clientMongoRepository;

    private BookServiceImpl bookService;
    private RentServiceImpl rentService;
    private ClientServiceImpl clientService;

    private BookRedisRepository bookRedisRepository;
    private RentRedisRepository rentRedisRepository;
    private ClientRedisRepository clientRedisRepository;
    private ClientRepository clientRepository;

    @BeforeEach
    void beforeEach() {
        this.bookMongoRepository = new BookMongoRepository();
        this.rentMongoRepository = new RentMongoRepository();
        this.clientMongoRepository = new ClientMongoRepository();

        this.bookRedisRepository = new BookRedisRepository();
        this.clientRedisRepository = new ClientRedisRepository();
        this.rentRedisRepository = new RentRedisRepository();

        this.clientService = new ClientServiceImpl();
        this.bookService = new BookServiceImpl();
        this.rentService = new RentServiceImpl();

        this.clientRepository = new ClientRepository(clientRedisRepository, clientMongoRepository);

        clientMongoRepository.clearDatabase();
        rentMongoRepository.clearDatabase();
        bookMongoRepository.clearDatabase();

        bookRedisRepository.clearThis();
        clientRedisRepository.clearThis();
        rentRedisRepository.clearThis();
    }

    @Test
    void redisConnectionTest() {
        BookRd book = new BookRd("someTitle", "someAuthor", "123", "someGenre");
        Client client = new Adult(UUID.randomUUID(),"Szymon", "Zakrzewski", "123", 21);
        clientRepository.add(client);
    }

    @Test
    void simpleBookRepositoryTest() {
        bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        BookMgd book = bookService.getBook("123");
        assertThat(book.getTitle()).isEqualTo("someTitle");
        assertThat(book.getAuthor()).isEqualTo("someAuthor");
        assertThat(book.getGenre()).isEqualTo("someGenre");
        assertThat(book.isRented()).isFalse();
        book.setRented(true);
        bookMongoRepository.updateBook(book);
        book = bookService.getBook("123");
        assertThat(book.isRented()).isTrue();

        bookService.registerBook("someTitle2", "someAuthor2", "123123", "someGenre2");
        BookMgd book2 = bookService.getBook("123123");
        assertThat(book2.getTitle()).isEqualTo("someTitle2");
        assertThat(book2.getAuthor()).isEqualTo("someAuthor2");
        assertThat(book2.getGenre()).isEqualTo("someGenre2");

        List<BookMgd> books = new ArrayList<>();
        books.addAll(bookService.findAllBooks());
        assertThat(books.size()).isEqualTo(2);
        bookService.unregisterBook(book2);
        books.removeAll(books);
        books.addAll(bookService.findAllBooks());
        assertThat(books.size()).isEqualTo(1);
    }

    @Test
    void simpleClientRepositoryTest() {
        List<Client> clients = new ArrayList<>();
        Client client = clientService.addClient("Szymon", "Zakrzewski", "123", 21);
        assertThat(client.toString()).isEqualTo("adult");

        clientService.addClient("someName", "someLastName", "123123", 8);

        clients.addAll(clientService.findAllClients());
        assertThat(clients.size()).isEqualTo(2);
        clientService.deleteClient(client);
        clients.removeAll(clients);
        clients.addAll(clientService.findAllClients());
        assertThat(clients.size()).isEqualTo(1);
    }

    @Test
    void simpleRentRepositoryTest() {
        bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        BookMgd book = bookService.getBook("123");
        Client client = clientService.addClient("Szymon", "Zakrzewski", "0123", 21);
        rentService.rentBook("0123", "123");
        RentMgd rent = rentService.getRentByBook("123");
        book = bookMongoRepository.findBySerialNumber("123");
        assertThat(rent.getBook()).isEqualTo(book);
        rent = rentService.getRentByClient("0123");
        assertThat(rent.getClient().getFirstName()).isEqualTo(client.getFirstName());
    }

    @Test
    void rentRentedBookTest() {
        List<RentMgd> rents = new ArrayList<>();
        clientService.addClient("Szymon", "Zakrzewski", "0123", 21);
        bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        BookMgd book = bookService.getBook("123");
        rentService.rentBook("0123", "123");
        RentMgd rent = rentService.getRentByBook("123");
        rentService.rentBook("0123", "123");
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(1);

        bookService.registerBook("someTitle2", "someAuthor2", "1230", "someGenre2");
        BookMgd book2 = bookService.getBook("1230");
        bookService.registerBook("someTitle3", "someAuthor3", "1231", "someGenre3");
        BookMgd book3 = bookService.getBook("1231");
        rentService.rentBook("0123", "1230");
        rentService.rentBook("0123", "1231");
        rents.removeAll(rents);
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(3);
        RentMgd rent2 = rentService.getRentByClient("0123");
        System.out.println(rent2);
    }

    @Test
    void returnBookTest() {
        List<RentMgd> rents = new ArrayList<>();
        clientService.addClient("Szymon", "Zakrzewski", "0123", 21);
        bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        BookMgd book = bookService.getBook("123");
        rentService.rentBook("0123", "123");
        RentMgd rent = rentService.getRentByBook("123");
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(1);

        book = bookMongoRepository.findBySerialNumber("123");
        rentService.returnBook(book);
        rents.removeAll(rents);
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(0);
    }
}
