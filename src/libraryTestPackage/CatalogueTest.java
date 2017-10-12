package libraryTestPackage;

import libraryPackage.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {
    Catalogue catalogue = new Catalogue();

    @Test
    public void defaultConstructorReturnsEmptyCatalogue() {
        assertTrue(this.catalogue.getAllIdAnBookQuantities().isEmpty());
    }

    @Test
    public void addBookFromStringIfHasNoSuchBook() throws Exception {
        this.catalogue.addBookFromString("1, title, author, 2000");
        Book expected = new Book(1, "title", "author", "2000");

        assertTrue(expected.compare(this.catalogue.getBookById(1)));
    }

    @Test
    public void addBookFromStringIfHasSuchBookThrowsObjectDuplicationException() throws Exception {
        this.catalogue.addBookFromString("1, title, author, 2000");

        assertThrows(ObjectDuplicationException.class, () ->
        {
            this.catalogue.addBookFromString("1, title2, author, 2000");
        });
    }

    @Test
    public void addBookFromStringIfWrongInformationFormatThrowsException() {
        assertThrows(Exception.class, () ->
        {
            this.catalogue.addBookFromString("a, title, author, 2000");
        });
    }

    @Test
    public void addEditionFromStringIfHasNoSuchEdition() throws Exception {
        this.catalogue.addBookFromString("1, title, author, 2000");
        this.catalogue.addEditionFromString("1, 123, 2020");
        Edition expected = new Edition("123", "2020");

        assertTrue(expected.compare(this.catalogue.getBookEntryById(1).getEditionByIsbn("123")));
    }

    @Test
    public void addEditionFromStringIfHasSuchEditionThrowsObjectDuplicationException () throws Exception {
        this.catalogue.addBookFromString("1, title, author, 2000");
        this.catalogue.addEditionFromString("1, 123, 2020");

        assertThrows(ObjectDuplicationException.class, () ->
        {
            this.catalogue.addEditionFromString("1, 123, 2020");
        });
    }

    @Test
    public void addEditionFromStringIfWrongIdThrowsBookNotFoundException () throws Exception {
        this.catalogue.addBookFromString("1, title, author, 2000");

        assertThrows(BookNotFoundException.class, () ->
        {
            this.catalogue.addEditionFromString("2, 123, 2020");
        });
    }

    @Test
    public void addEditionFromStringIfWrongInformationFormatThrowsException() throws Exception {
        this.catalogue.addBookFromString("1, title, author, 2000");

        assertThrows(Exception.class, () ->
        {
            this.catalogue.addEditionFromString("s, 123, 2020");
        });
    }

    @Test
    public void addBookEntryFromStringIfHasNoSuchBookEntry() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        BookEntry expected = new BookEntry(new Book(1, "title", "author", "2000"));
        expected.addEdition(new Edition("123", "2020"));

        assertTrue(expected.compare(this.catalogue.getBookEntryById(1)));
    }

    @Test
    public void addBookEntryFromStringIfHasSuchBookEntryThrowsObjectDuplicationException() throws Exception {
        this.catalogue.addBookFromString("1, title, author, 2000");

        assertThrows(ObjectDuplicationException.class, () ->
        {
            this.catalogue.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        });
    }

    @Test
    public void addBookEntryFromStringIfWrongInformationFormatThrowsException() {
        assertThrows(Exception.class, () ->
        {
            this.catalogue.addBookEntryFromString("s, title, author, 2000, 123, 2020");
        });
    }

    @Test
    public void getBookEntryByIdIfHasSuch() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        BookEntry expected = new BookEntry(new Book(1, "title", "author", "2000"));
        expected.addEdition(new Edition("123", "2020"));

        assertTrue(expected.compare(this.catalogue.getBookEntryById(1)));
    }

    @Test
    public void getBookEntryByIdIfHasNotSuch() throws Exception {
        assertThrows(BookNotFoundException.class, () ->
        {
            this.catalogue.getBookEntryById(1);
        });
    }

    @Test
    public void getBookEditionByIsbnIfHasSuch() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        BookEdition expected = new BookEdition(new Book(1, "title", "author", "2000"), new Edition("123", "2020"));

        assertTrue(expected.compare(this.catalogue.getBookEditionByIsbn("123")));
    }

    @Test
    public void getBookEditionByIsbnIfHasNoSuch() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, author, 2000, 123, 2020");

        assertThrows(BookNotFoundException.class, () ->
        {
            this.catalogue.getBookEditionByIsbn("124");
        });
    }

    @Test
    public void getBookEntriesByTitleIfHasSuch() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        this.catalogue.addBookEntryFromString("2, title2, author, 2000, 123, 2020");
        this.catalogue.addBookEntryFromString("3, tytul, author, 2000, 123, 2020");
        BookEntry expected1 = new BookEntry(new Book(1, "title", "author", "2000"));
        expected1.addEdition(new Edition("123", "2020"));
        BookEntry expected2 = new BookEntry(new Book(2, "title2", "author", "2000"));
        expected2.addEdition(new Edition("123", "2020"));
        Set<BookEntry> expected = new HashSet<>();
        expected.add(expected1);
        expected.add(expected2);


        assertEquals(expected, this.catalogue.getBookEntriesByTitle("titl"));
    }

    @Test
    public void getBookEntriesByTitleIfHasNoSuch() {
        assertThrows(BookNotFoundException.class, () ->
        {
            this.catalogue.getBookEntriesByTitle("title");
        });
    }

    @Test
    public void getBookEntriesByAuthorIfHasSuch() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        this.catalogue.addBookEntryFromString("2, title2, name2 lastName, 2000, 123, 2020");
        this.catalogue.addBookEntryFromString("3, title, author, 2000, 123, 2020");
        BookEntry expected1 = new BookEntry(new Book(1, "title", "name1 lastName", "2000"));
        expected1.addEdition(new Edition("123", "2020"));
        BookEntry expected2 = new BookEntry(new Book(2, "title2", "name2 lastName", "2000"));
        expected2.addEdition(new Edition("123", "2020"));
        Set<BookEntry> expected = new HashSet<>();
        expected.add(expected1);
        expected.add(expected2);

        assertTrue(expected.equals(this.catalogue.getBookEntriesByAuthor("lastName")));
    }

    @Test
    public void getBookEntriesByAuthorIfHasNoSuch() {
        assertThrows(BookNotFoundException.class, () ->
        {
            this.catalogue.getBookEntriesByAuthor("author");
        });
    }

    @Test
    public void borrowBookIfAvailable() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        this.catalogue.getBookEntryById(1).getEditionByIsbn("123").addQuantity(1);
        this.catalogue.borrowBook(1, "123");
        assertEquals(1, this.catalogue.getBookEntryById(1).getEditionByIsbn("123").getBorrowed());
    }

    @Test
    public void borrowBookIfUnavailable() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        assertThrows(Exception.class, () ->
        {
            this.catalogue.borrowBook(1, "123");
        });
    }

    @Test
    public void borrowBookIfWrongId() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        assertThrows(BookNotFoundException.class, () ->
        {
            this.catalogue.borrowBook(2, "123");
        });
    }

    @Test
    public void borrowBookIfWrongEdition() throws Exception {
        this.catalogue.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        assertThrows(EditionNotFoundException.class, () ->
        {
            this.catalogue.borrowBook(1, "1232");
        });
    }
}