package libraryTestPackage;

import libraryPackage.Book;
import libraryPackage.BookEntry;
import libraryPackage.Edition;
import libraryPackage.EditionNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookEntryTest {
    @Test
    public void testGetEditions() throws EditionNotFoundException {
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry.addEdition(edition1);
        assertEquals(editions, bookEntry.getEditions());
        assertTrue(edition1.compare(bookEntry.getEditionByIsbn("123")));
    }

    @Test
    public void getEditionByIsbnIfHasSuch() throws EditionNotFoundException {
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry.addEdition(edition1);
        assertTrue(edition1.compare(bookEntry.getEditionByIsbn("123")));
    }

    @Test
    public void getEditionByIsbnIfHasNoSuchThrowsException() throws EditionNotFoundException {
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"));
        bookEntry.addEdition(edition1);
        assertThrows(EditionNotFoundException.class, () ->
        {
            bookEntry.getEditionByIsbn("124");
        });
    }

    @Test
    public void testAddEditionIfHasSuch() throws EditionNotFoundException {
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
    public void testAddEdition() throws EditionNotFoundException {
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
        bookEntry2.addEdition(new Edition("123", "0030"));
        assertFalse(bookEntry1.compare(bookEntry2));
    }

    @Test
    public void testToString() {
        String expected = "1, title, author, date1\n\teditions:\n\t123, date2, 0";
        BookEntry bookEntry =  new BookEntry(new Book(1, "title", "author", "date1"));
        bookEntry.addEdition(new Edition("123", "date2"));
        assertEquals(expected, bookEntry.toString());
    }

    @Test
    public void compareEditionsIfSame() {
        Set<Edition> editions1 = new HashSet<>();
        Set<Edition> editions2 = new HashSet<>();
        editions1.add(new Edition("123", "0000"));
        editions1.add(new Edition("124", "0000"));
        editions2.add(new Edition("123", "0000"));
        editions2.add(new Edition("124", "0000"));
        BookEntry bookEntry =  new BookEntry(new Book(1, "title", "author", "date1"));
        assertTrue(bookEntry.compareEditions(editions1, editions2));
    }

    @Test
    public void compareEditionsIfDifferent() {
        Set<Edition> editions1 = new HashSet<>();
        Set<Edition> editions2 = new HashSet<>();
        editions1.add(new Edition("123", "0000"));
        editions1.add(new Edition("124", "0001"));
        editions2.add(new Edition("123", "0000"));
        editions2.add(new Edition("124", "0000"));
        BookEntry bookEntry =  new BookEntry(new Book(1, "title", "author", "date1"));
        assertFalse(bookEntry.compareEditions(editions1, editions2));
    }
}