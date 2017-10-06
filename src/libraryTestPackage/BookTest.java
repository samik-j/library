package libraryTestPackage;

import libraryPackage.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    public void compareIfSame() {
        Book book1 = new Book(1, 123, "title", "author", "2017");
        Book book2 = new Book(1, 123, "title", "author", "2017");
        assertTrue(book1.compare(book2));
    }

    @Test
    public void compareIfDifferent() {
        Book book1 = new Book(1, 123, "title", "author", "2017");
        Book book2 = new Book(1, 1234, "title", "author", "2017");
        assertFalse(book1.compare(book2));
    }

    @Test
    public void testToString() {
        Book book1 = new Book(1, 123, "title", "author", "2017");
        assertEquals("1, 123, title, author, 2017", book1.toString());
    }
}