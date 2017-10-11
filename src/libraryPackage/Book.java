package libraryPackage;

public class Book {
    private final int id;
    private String title;
    private String author;
    private String originalPublicationYear; // change name

    public Book(int _id, String _title, String _author, String _originalPublicationYear) {
        this.id = _id;
        this.title = _title;
        this.author = _author;
        this.originalPublicationYear = _originalPublicationYear;
    }

    public int getId() {
        return this.id;
    }

    public boolean compare(final Book book) {
        return this.id == book.id && this.title.equals(book.title)
                && this.author.equals(book.author) && this.originalPublicationYear.equals(book.originalPublicationYear);
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
        return  "" + this.id + ", " + this.title + ", " + this.author + ", " + this.originalPublicationYear;
    }
}
