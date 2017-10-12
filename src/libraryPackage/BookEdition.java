package libraryPackage;

public class BookEdition {
    private Book book;
    private Edition edition;

    public BookEdition(Book _book, Edition _edition) {
        this.book = _book;
        this.edition = _edition;
    }

    public boolean compare(BookEdition bookEdition) {
        return this.book.compare(bookEdition.book) && this.edition.compare(bookEdition.edition);
    }

    @Override
    public String toString() {
        return "" + book + ", " + edition;
    }
}
