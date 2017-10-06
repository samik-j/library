package libraryPackage;

public class Book {
    private final int id;
    private int isbn;
    private String title;
    private String author;
    private String publicationDate;

    public Book(int _id, int _isbn, String _title, String _author, String _publicationDate) {
        this.id = _id;
        this.isbn = _isbn;
        this.title = _title;
        this.author = _author;
        this.publicationDate = _publicationDate;
    }

    public int getId() {
        return this.id;
    }

    public int gteIsbn() {
        return this.isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPublicationDate() {
        return this.publicationDate;
    }

    public boolean compare(Book book) {
        return this.id == book.id && this.isbn == book.isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return  "" + this.id + ", " + this.isbn + ", " + this.title + ", " + this.author + ", " + this.publicationDate;
    }
}
