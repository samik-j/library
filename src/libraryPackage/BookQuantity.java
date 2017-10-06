package libraryPackage;

import java.util.Map;

public class BookQuantity {

    private final Book book;
    private int quantity;

    public BookQuantity(Book _book, int _quantity) {
        this.book = _book;
        this.quantity = _quantity;
    }

    public Book getBook() {
        return this.book;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int addQuantity(final int quantity) {
        return this.quantity + quantity;
    }

    public boolean compare(BookQuantity bookQ) {
        return this.book.compare(bookQ.book) && this.quantity == bookQ.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookQuantity that = (BookQuantity) o;

        if (this.quantity != that.quantity) return false;
        return this.book != null ? this.book.equals(that.book) : that.book == null;
    }

    @Override
    public String toString() {
        return "" + book + ", " + quantity;
    }
}
