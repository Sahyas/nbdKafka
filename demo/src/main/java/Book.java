public class Book {
    private String title;
    private String author;
    private String serialNumber;
    private String genre;
    boolean IsArchive = false;

    public Book() {
    }

    public Book(String title, String author, String serialNumber, String genre) {
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isArchive() {
        return IsArchive;
    }

    public void setArchive(boolean archive) {
        IsArchive = archive;
    }
}
