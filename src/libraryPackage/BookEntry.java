package libraryPackage;

public class BookEntry {
    private final int id;
    private final String isbn;
    private Book book;
    private int quantity;

    public BookEntry(int _id, String _isbn, Book _book, int quantity) {
        this.id = _id;
        this.isbn = _isbn;
        this.book = _book;
        this.quantity = quantity;
    }
}
