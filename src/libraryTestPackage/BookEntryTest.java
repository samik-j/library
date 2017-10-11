package libraryTestPackage;

import libraryPackage.Book;
import libraryPackage.BookEntry;
import libraryPackage.Edition;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookEntryTest {
    @Test
    public void testGetEditions() {
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry.addEdition(edition1);
        assertEquals(editions, bookEntry.getEditions());
        assertTrue(edition1.compare(bookEntry.getEditionByIsbn("123")));
    }

    @Test
    public void getEditionByIsbnIfHasSuch() {
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry.addEdition(edition1);
        assertTrue(edition1.compare(bookEntry.getEditionByIsbn("123")));
    }

    @Test
    public void getEditionByIsbnIfHasNoSuchReturnsNull() {
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry.addEdition(edition1);
        assertNull(bookEntry.getEditionByIsbn("124"));
    }

    @Test
    public void testAddEditionIfHasSuch() {
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry.addEdition(edition1);
        Edition edition2 = new Edition("123", "2000");
        bookEntry.addEdition(edition2);
        assertEquals(editions, bookEntry.getEditions());
        assertTrue(edition1.compare(bookEntry.getEditionByIsbn("123")));
    }

    @Test
    public void testAddEdition() {
        Edition edition1 = new Edition("123", "0000");
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry.addEdition(edition1);
        assertTrue(edition1.compare(bookEntry.getEditionByIsbn("123")));
    }

    @Test
    public void compareIfSame() {
        Edition edition1 = new Edition("123", "0000");
        BookEntry bookEntry1 = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry1.addEdition(edition1);
        BookEntry bookEntry2 = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry2.addEdition(edition1);
        assertTrue(bookEntry1.compare(bookEntry2));
    }

    @Test
    public void compareIfDifferentWhenDifferentBook() {
        Edition edition1 = new Edition("123", "0000");
        BookEntry bookEntry1 = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry1.addEdition(edition1);
        BookEntry bookEntry2 = new BookEntry(new Book(1, "title2", "author", "0000"));
        bookEntry2.addEdition(edition1);
        assertFalse(bookEntry1.compare(bookEntry2));
    }

    @Test
    public void compareIfDifferentWhenDifferentEditions() {
        BookEntry bookEntry1 = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry1.addEdition(new Edition("123", "0000"));
        BookEntry bookEntry2 = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry2.addEdition(new Edition("124", "0000"));
        assertFalse(bookEntry1.compare(bookEntry2));
    }

}