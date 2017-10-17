package libraryTestPackage;

import libraryPackage.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class LibraryTest {
    Library library;
    FileHandler fileHandler = mock(FileHandler.class);

    @Test
    public void testReadCatalogueWithEmptyFile() throws Exception {
        when(fileHandler.readCatalogue()).thenReturn(new HashMap<>());
        library = new Library(fileHandler);

        assertTrue(library.getCatalogue().isEmpty());
    }

    @Test
    public void testReadCatalogue() throws Exception {
        Map<Integer, Book> catalogue = new HashMap<>();
        catalogue.put(1, new Book(new String[]{"1", "title", "author", "date"}));
        when(fileHandler.readCatalogue()).thenReturn(catalogue);
        library = new Library(fileHandler);

        assertEquals(1, library.getCatalogue().size());
    }

    @Test//nowy
    public void testReadMembersWithEmptyFile() throws Exception {
        when(fileHandler.readMembers()).thenReturn(new HashMap<>());
        library = new Library(fileHandler);

        assertTrue(library.getMembers().isEmpty());
    }

    @Test//nowy
    public void testReadMembers() throws Exception {
        Map<Integer, Person> members = new HashMap<>();
        members.put(1, new Person(new String[]{"1", "name"}));
        when(fileHandler.readMembers()).thenReturn(members);
        library = new Library(fileHandler);

        assertEquals(1, library.getMembers().size());
    }

    @Test
    public void getBookById() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, date");
        Book expected = new Book(new String[]{"1", "title", "author", "date"});

        assertTrue(expected.compare(this.library.getBookById(1)));
    }

    @Test
    public void getBooksByTitleWhenHasSuch() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, date");
        this.library.addBookFromString("2, something, author, date");
        Set<Book> expected = new HashSet<>();
        expected.add(new Book(new String[]{"1", "title", "author", "date"}));

        Set<Book> result = this.library.getBooksByTitle("titl");

        assertEquals(1, result.size());
        assertEquals(expected, result);
    }

    @Test
    public void getBooksByTitleWhenHasNoSuch() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, date");
        this.library.addBookFromString("2, something, author, date");

        assertTrue(this.library.getBooksByTitle("as").isEmpty());
    }

    @Test
    public void getBooksByAuthorWhenHasSuch() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, date");
        this.library.addBookFromString("2, something, author, date");
        this.library.addBookFromString("3, something, someone, date");
        Set<Book> expected = new HashSet<>();
        expected.add(new Book(new String[]{"1", "title", "author", "date"}));
        expected.add(new Book(new String[]{"2", "something", "author", "date"}));

        Set<Book> result = this.library.getBooksByAuthor("auth");

        assertEquals(2, result.size());
        assertEquals(expected, result);
    }

    @Test
    public void getBooksByAuthorWhenHasNoSuch() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, date");
        this.library.addBookFromString("2, something, author, date");

        assertTrue(this.library.getBooksByAuthor("someone").isEmpty());
    }

    @Test
    public void getBookEditionByIsbnIfHasSuch() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, 2000");
        this.library.addEditionFromStringToBook(1, "1, 123, 2020");

        Book expectedBook = new Book(new String[]{"1", "title", "author", "2000"});
        Edition expectedEdition = new Edition(new String[]{"1", "123", "2020"});
        expectedBook.getEditions().add(expectedEdition);
        BookEdition expected = new BookEdition(expectedBook, expectedEdition);

        assertTrue(expected.compare(this.library.getBookEditionByIsbn("123")));
    }

    @Test
    public void getBookEditionByIsbnIfHasNoSuchThrowsEditionNotFoundException() throws Exception {
        this.getLibrary();

        assertThrows(EditionNotFoundException.class, () ->
        {
            this.library.getBookEditionByIsbn("123");
        });
    }

    @Test
    public void addBookFromStringIfPossible() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, 2000");
        Book expected = new Book(new String[]{"1", "title", "author", "2000"});

        assertTrue(expected.compare(this.library.getBookById(1)));
    }

    @Test
    public void addBookFromStringThrowsIfWrongInformationFormatException() throws Exception {
        this.getLibrary();

        assertThrows(Exception.class, () ->
        {
            this.library.addBookFromString("a, title, author, 2000");
        });
    }

    @Test
    public void addBookFromStringIfLibraryHasSuchBookThrowsObjectDuplicationException() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, 2000");

        assertThrows(ObjectDuplicationException.class, () ->
        {
            this.library.addBookFromString("1, title, author, 2000");
        });
    }

    @Test
    public void addEditionFromStringToBookIfPossible() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, 2000");
        this.library.addEditionFromStringToBook(1, "1, 123, 2020");
        Edition expected = new Edition(new String[]{"1", "123", "2020"});

        assertEquals(1, this.library.getBookById(1).getEditions().size());
        assertTrue(expected.compare(this.library.getBookById(1).getEditionById(1)));
    }

    @Test
    public void addEditionFromStringToBookIfWrongInformationFormatThrowsException() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, 2000");

        assertThrows(Exception.class, () ->
        {
            this.library.addEditionFromStringToBook(1, "a, 123, 2000");
        });
    }

    @Test
    public void addEditionFromStringToBookIfNoSuchBookIdThrowsBookNotFoundException() throws Exception {
        this.getLibrary();

        assertThrows(BookNotFoundException.class, () ->
        {
            this.library.addEditionFromStringToBook(1, "a, 123, 2000");
        });
    }

    @Test
    public void addPresonFromStringIfPossible() throws Exception {
        this.getLibrary();
        this.library.addPersonFromString("1, name");

        assertEquals(1, this.library.getMembers().size());
    }

    @Test
    public void addPersonFromStrinfIfHasSuchIdThrowsObjectDuplicationException() throws Exception {
        this.getLibrary();
        this.library.addPersonFromString("1, name");

        assertThrows(ObjectDuplicationException.class, () ->
        {
            this.library.addPersonFromString("1, name");
        });
    }

    @Test
    public void addPersonFromStringIfWrongInputThrowsException() throws Exception {
        this.getLibrary();

        assertThrows(Exception.class, () ->
        {
            this.library.addPersonFromString("a, name");
        });
    }

    @Test
    public void borrowIfPossible() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, 2000");
        this.library.addEditionFromStringToBook(1, "1, 123, 2020, 5, 0");

        this.library.borrowBook(1, 1);
        assertEquals(1, this.library.getBookById(1).getEditionById(1).getBorrowed());
    }

    @Test
    public void borrowIfNoSuchBookIdThrowsBookNotFoundException() throws Exception {
        this.getLibrary();

        assertThrows(BookNotFoundException.class, () ->
        {
            this.library.borrowBook(1, 1);
        });
    }

    @Test
    public void borrowIfNoSuchEditionIdThrowsEditionNotFoundException() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, 2000");

        assertThrows(EditionNotFoundException.class, () ->
        {
            this.library.borrowBook(1, 1);
        });
    }

    @Test
    public void borrowIfNotPossibleThrowsException() throws Exception {
        this.getLibrary();
        this.library.addBookFromString("1, title, author, 2000");
        this.library.addEditionFromStringToBook(1, "1, 123, 2020, 5, 5");

        assertThrows(Exception.class, () ->
        {
            this.library.borrowBook(1, 1);
        });
    }

    @Test//ten sie zmienil
    public void saveLibrary() throws Exception {
        this.getLibrary();
        this.library.saveLibrary();

        verify(fileHandler, times(1)).saveCatalogue(this.library.getCatalogue());
        verify(fileHandler, times(1)).saveMembers(this.library.getMembers());
    }

    private void getLibrary() throws Exception { //to sie zmienilo
        when(fileHandler.readCatalogue()).thenReturn(new HashMap<>());
        when(fileHandler.readMembers()).thenReturn(new HashMap<>());
        library = new Library(fileHandler);
    }

}