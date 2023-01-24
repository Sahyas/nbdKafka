import com.nbd.model.Adult;
import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;
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
        this.rentService = new RentServiceImpl(clientService, bookService);

        this.clientRepository = new ClientRepository(clientRedisRepository, clientMongoRepository);

        clientMongoRepository.clearDatabase();
        rentMongoRepository.clearDatabase();
        bookMongoRepository.clearDatabase();

        bookRedisRepository.clearCache();
        clientRedisRepository.clearCache();
        rentRedisRepository.clearCache();
    }

    @Test
    void redisSaveTest() {
        Client client = new Adult("Szymon", "Zakrzewski", "123", 21);
        clientRedisRepository.add(client);
        Client foundClient = clientRedisRepository.getById(client.getId()).get();
        assertThat(client.toString()).isEqualTo(foundClient.toString());

        Book book = new Book("someTitle", "someAuthor", "123", "someGenre");
        bookRedisRepository.add(book);
        Book foundBook = bookRedisRepository.getById(book.getId()).get();
        assertThat(book.toString()).isEqualTo(foundBook.toString());

        Rent rent = new Rent(client, book);
        rentRedisRepository.add(rent);
        Rent foundRent = rentRedisRepository.getById(rent.getId()).get();
        assertThat(rent.toString()).isEqualTo(foundRent.toString());
    }

    @Test
    void redisClearCacheTest() {
        Book book = new Book("someTitle", "someAuthor", "123", "someGenre");
        bookRedisRepository.add(book);
        Book book2 = new Book("someTitle2", "someAuthor2", "1232", "someGenre2");
        bookRedisRepository.add(book2);
        Book book3 = new Book("someTitle3", "someAuthor3", "1233", "someGenre3");
        bookRedisRepository.add(book3);
        assertThat(bookRedisRepository.findAll().size()).isEqualTo(3);
        rentRedisRepository.clearCache();
        assertThat(bookRedisRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void lostConnectionTest() throws Exception {
        Book book = bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        Book foundBook = bookService.getBookById(book.getId());
        assertThat(book).isEqualTo(foundBook);
        bookRedisRepository.close();
        assertThat(bookRedisRepository.checkConnection()).isFalse();
        Book foundBook2 = bookService.getBookById(book.getId());
        assertThat(book).isEqualTo(foundBook2);
    }

    @Test
    void simpleBookRepositoryTest() {
        Book book = bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        assertThat(book.getTitle()).isEqualTo("someTitle");
        assertThat(book.getAuthor()).isEqualTo("someAuthor");
        assertThat(book.getGenre()).isEqualTo("someGenre");
        assertThat(book.isRented()).isFalse();
        book.setRented(true);
        bookService.updateBook(book);
        book = bookService.getBookById(book.getId());
        assertThat(book.isRented()).isTrue();

        Book book2 = bookService.registerBook("someTitle2", "someAuthor2", "123123", "someGenre2");
        assertThat(book2.getTitle()).isEqualTo("someTitle2");
        assertThat(book2.getAuthor()).isEqualTo("someAuthor2");
        assertThat(book2.getGenre()).isEqualTo("someGenre2");

        List<Book> books = new ArrayList<>();
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
        assertThat(client.getTypeInfo()).isEqualTo("ADULT");

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
        Book book = bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        Client client = clientService.addClient("Szymon", "Zakrzewski", "0123", 21);
        rentService.rentBook(client.getId(), book.getId());
        Rent rent = rentService.getRentByBook(book);
        book = bookService.getBookById(book.getId());
        assertThat(rent.getBook()).isEqualTo(book);
        rent = rentService.getRentByClient(client);
        assertThat(rent.getClient().getFirstName()).isEqualTo(client.getFirstName());
    }

    @Test
    void rentRentedBookTest() {
        List<Rent> rents = new ArrayList<>();
        Client client = clientService.addClient("Szymon", "Zakrzewski", "0123", 21);
        Book book = bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        rentService.rentBook(client.getId(), book.getId());
        Rent rent = rentService.getRentByBook(book);
        rentService.rentBook(client.getId(), book.getId());
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(1);
    }

    @Test
    void returnBookTest() {
        List<Rent> rents = new ArrayList<>();
        Client client = clientService.addClient("Szymon", "Zakrzewski", "0123", 21);
        Book book = bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        rentService.rentBook(client.getId(), book.getId());
        Rent rent = rentService.getRentByBook(book);
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(1);

        book = bookService.getBookById(book.getId());
        rentService.returnBook(book);
        rents.removeAll(rents);
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(0);
    }


}
