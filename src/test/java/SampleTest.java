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

        clientRepository.clearDatabase();
        rentRepository.clearDatabase();
        bookRepository.clearDatabase();
    }

    @Test
    void simpleBookRepositoryTest() {
        bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        Book book = bookService.getBook("123");
        assertThat(book.getTitle()).isEqualTo("someTitle");
        assertThat(book.getAuthor()).isEqualTo("someAuthor");
        assertThat(book.getGenre()).isEqualTo("someGenre");
        assertThat(book.isRented()).isFalse();
        book.setRented(true);
        bookRepository.updateBook(book);
        book = bookService.getBook("123");
        assertThat(book.isRented()).isTrue();

        bookService.registerBook("someTitle2", "someAuthor2", "123123", "someGenre2");
        Book book2 = bookService.getBook("123123");
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
        clientService.addClient("Szymon", "Zakrzewski", "123", 21);
        Client client = clientService.getClientByPersonalId("123");
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
        bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        Book book = bookService.getBook("123");
        clientService.addClient("Szymon", "Zakrzewski", "0123", 21);
        Client client = clientService.getClientByPersonalId("0123");
        rentService.rentBook("0123", "123");
        Rent rent = rentService.getRentByBook("123");
        book = bookRepository.findBySerialNumber("123");
        assertThat(rent.getBook()).isEqualTo(book);
        rent = rentService.getRentByClient("0123");
        assertThat(rent.getClient()).isEqualTo(client);
    }

    @Test
    void rentRentedBookTest() {
        List<Rent> rents = new ArrayList<>();
        clientService.addClient("Szymon", "Zakrzewski", "0123", 21);
        bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        Book book = bookService.getBook("123");
        rentService.rentBook("0123", "123");
        Rent rent = rentService.getRentByBook("123");
        rentService.rentBook("0123", "123");
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(1);

        bookService.registerBook("someTitle2", "someAuthor2", "1230", "someGenre2");
        Book book2 = bookService.getBook("1230");
        bookService.registerBook("someTitle3", "someAuthor3", "1231", "someGenre3");
        Book book3 = bookService.getBook("1231");
        rentService.rentBook("0123", "1230");
        rentService.rentBook("0123", "1231");
        rents.removeAll(rents);
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(3);
        Rent rent2 = rentService.getRentByClient("0123");
        System.out.println(rent2);
    }

    @Test
    void returnBookTest() {
        List<Rent> rents = new ArrayList<>();
        clientService.addClient("Szymon", "Zakrzewski", "0123", 21);
        bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        Book book = bookService.getBook("123");
        rentService.rentBook("0123", "123");
        Rent rent = rentService.getRentByBook("123");
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(1);

        book = bookRepository.findBySerialNumber("123");
        rentService.returnBook(book);
        rents.removeAll(rents);
        rents.addAll(rentService.findAllCurrentRents());
        assertThat(rents.size()).isEqualTo(0);
    }
}
