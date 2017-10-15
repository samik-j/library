package libraryTestPackage;

import libraryPackage.Book;
import libraryPackage.BookEdition;
import libraryPackage.BookEntry;
import libraryPackage.Edition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookEditionTest {

    @Test
    public void compareIfSame() throws Exception {
        BookEdition bookEdition1 = new BookEdition(new Book(new String[]{"1", "title", "author", "1000"}), new Edition(new String[]{"1", "123", "2020"}));
        BookEdition bookEdition2 = new BookEdition(new Book(new String[]{"1", "title", "author", "1000"}), new Edition(new String[]{"1", "123", "2020"}));

        assertTrue(bookEdition1.compare(bookEdition2));
    }

    @Test
    public void compareIfDifferentBooks() throws Exception {
        BookEdition bookEdition1 = new BookEdition(new Book(new String[]{"1", "title", "author", "1000"}), new Edition(new String[]{"1", "123", "2020"}));
        BookEdition bookEdition2 = new BookEdition(new Book(new String[]{"1", "title2", "author", "1000"}), new Edition(new String[]{"1", "123", "2020"}));

        assertFalse(bookEdition1.compare(bookEdition2));
    }

    @Test
    public void compareIfDifferentEditions() throws Exception {
        BookEdition bookEdition1 = new BookEdition(new Book(new String[]{"1", "title", "author", "1000"}), new Edition(new String[]{"1", "123", "2020"}));
        BookEdition bookEdition2 = new BookEdition(new Book(new String[]{"1", "title", "author", "1000"}), new Edition(new String[]{"1", "124", "2020"}));

        assertFalse(bookEdition1.compare(bookEdition2));
    }

    @Test
    public void testToString() throws Exception {
        BookEdition bookEdition1 = new BookEdition(new Book(new String[]{"1", "title", "author", "1000"}), new Edition(new String[]{"1", "123", "2020"}));
        String expected = "id 1.1 | title, author, 123, 2020, quantity: 0, borrowed: 0";

        assertEquals(expected, bookEdition1.toString());
    }
}