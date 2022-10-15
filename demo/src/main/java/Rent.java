import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.UUID;

public class Rent {

    private UUID id;
    private Date beginTime;
    private Date endTime;
    Book book = new Book();
    Client client = new Client();

    public Rent(UUID id, Book book, Client client) {
        this.id = id;
        this.book = book;
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Book getBook() {
        return book;
    }

    public Client getClient() {
        return client;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
