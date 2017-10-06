package libraryPackage;

import java.util.HashMap;
import java.util.Map;

public class Catalogue {
    private Map<Integer, BookQuantity> books;

    public Catalogue() {
        this.books = new HashMap<>();
    }

    public void addBookFromString(String book) throws Exception {
        try{
            final String[] bookInformation = book.split(", ");
            final int id = Integer.parseInt(bookInformation[0]);
            final String isbn = bookInformation[1];
            final String title = bookInformation[2];
            final String author = bookInformation[3];
            final String publicationDate = bookInformation[4];
            final int quantity = Integer.parseInt(bookInformation[5]);

            this.addBook(new Book(id, isbn, title, author, publicationDate), quantity);
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    public Map<Integer, BookQuantity> getAllIdAnBookQuantities() {
        return this.books;
    }

    public BookQuantity getBookQuantityById(final int id) {
        return this.books.get(id);
    }

    private void addBook(final Book book, final int quantity) throws MismatchBookIsbnException {
        if(this.books.containsKey(book.getId())) {
            if(this.getBookById(book.getId()).getIsbn().equals(book.getIsbn()))
                this.books.get(book.getId()).addQuantity(quantity);
            else
                throw new MismatchBookIsbnException("cannot add, ISBN not the same");
        }
        else
            this.books.put(book.getId(), new BookQuantity(book, quantity));
    }

    private Book getBookById(final int id) {
        return this.books.get(id).getBook();
    }
}
