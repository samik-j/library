package libraryTestPackage;

import libraryPackage.Book;
import libraryPackage.BookQuantity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookQuantityTest {

    @Test
    public void testToString() {
        BookQuantity bookQuantity = new BookQuantity(new Book(1, "123", "title", "author", "2017"), 2);
        assertTrue(("1, 123, title, author, 2017, 2").equals(bookQuantity.toString()));
    }

    @Test
    public void compareIfSame() {
        BookQuantity bookQuantity1 = new BookQuantity(new Book(1, "123", "title", "author", "2017"), 2);
        BookQuantity bookQuantity2 = new BookQuantity(new Book(1, "123", "title", "author", "2017"), 2);
        assertTrue(bookQuantity1.compare(bookQuantity2));
    }

    @Test
    public void compareIfDifferentQuantity() {
        BookQuantity bookQuantity1 = new BookQuantity(new Book(1, "123", "title", "author", "2017"), 2);
        BookQuantity bookQuantity2 = new BookQuantity(new Book(1, "123", "title", "author", "2017"), 4);
        assertFalse(bookQuantity1.compare(bookQuantity2));
    }

    @Test
    public void compareIfDifferentBook() {
        BookQuantity bookQuantity1 = new BookQuantity(new Book(1, "123", "title", "author", "2017"), 2);
        BookQuantity bookQuantity2 = new BookQuantity(new Book(1, "1223", "title", "author", "2017"), 2);
        assertFalse(bookQuantity1.compare(bookQuantity2));
    }
}