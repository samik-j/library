package libraryTestPackage;

import libraryPackage.*;
import org.junit.jupiter.api.Test;

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
    public void addBookEntryFromStringThrowsBookNotFoundException() {// nie bedzie nigdy tak bo dodaje jednoczesnie book i edition a to edition moze to throwowac

    }

    @Test
    public void addBookEntryFromStringIfWrongInformationFormatThrowsException() {
        assertThrows(Exception.class, () ->
        {
            this.catalogue.addBookEntryFromString("s, title, author, 2000, 123, 2020");
        });
    }
}