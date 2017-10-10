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
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"), new HashSet<>(editions));
        assertEquals(editions, bookEntry.getEditions());
    }

    @Test
    public void getEditionByIsbnIfHasSuch() {
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"), new HashSet<>(editions));
        assertEquals(edition1, bookEntry.getEditionByIsbn("123"));
    }

    @Test
    public void getEditionByIsbnIfHasNoSuchReturnsNull() {
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"), new HashSet<>(editions));
        assertEquals(null, bookEntry.getEditionByIsbn("124"));
    }

    @Test
    public void testAddEditionIfHasSuch() { //czy to sie oplaca miec??
        Edition edition1 = new Edition("123", "0000");
        Set<Edition> editions = new HashSet<>();
        editions.add(edition1);
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"), new HashSet<>(editions));
        Edition edition2 = new Edition("123", "0000");
        bookEntry.addEdition(edition2);
        assertEquals(editions, bookEntry.getEditions());
    }

    @Test
    public void testAddEdition() {
        Edition edition1 = new Edition("123", "0000");
        BookEntry bookEntry = new BookEntry(new Book(1, "title", "author", "0000"), new HashSet<>());
        bookEntry.addEdition(edition1);
        assertEquals(edition1, bookEntry.getEditionByIsbn("123"));
    }

}