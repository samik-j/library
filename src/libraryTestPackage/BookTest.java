package libraryTestPackage;

import libraryPackage.Book;
import libraryPackage.Edition;
import libraryPackage.EditionNotFoundException;
import libraryPackage.ObjectDuplicationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    public void testConstructor() throws Exception {
        Book book = new Book(new String[]{"1", "title", "author", "2017"});

        assertEquals(book.getId(), 1);
        assertEquals(book.getTitle(), "title");
        assertEquals(book.getAuthor(), "author");
        assertEquals(book.getOriginalPublicationYear(), "2017");
        assertTrue(book.getEditions().isEmpty());
    }

    @Test
    public void testConstructorThrowsException() {
        assertThrows(Exception.class, () ->
        {
            new Book(new String[]{"a", "title", "author", "2017"});
        });
    }

    @Test
    public void hasEditionByIsbnReturnsTrue() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020, 0, 0");

        assertTrue(book1.hasEditionByIsbn("123"));
    }

    @Test
    public void hasEditionByIsbnReturnsFalse() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});

        assertFalse(book1.hasEditionByIsbn("123"));
    }

    @Test
    public void getEditionByIsbnIfHasSuch() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020, 0, 0");
        Edition expected = new Edition(new String[]{"1", "123", "2020"});

        assertTrue(expected.compare(book1.getEditionByIsbn("123")));
    }

    @Test
    public void getEditionByIsbnIfHasNoSuchThrowsEditionNotFoundException() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});

        assertThrows(EditionNotFoundException.class, () ->
        {
            book1.getEditionByIsbn("123");
        });
    }

    @Test
    public void getEditionByIdIfHasSuch() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020, 0, 0");
        Edition expected = new Edition(new String[]{"1", "123", "2020"});

        assertTrue(expected.compare(book1.getEditionById(1)));
    }

    @Test public void getEditionByIdIfHasNoSuchTrowsEditionNotFoundException() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});

        assertThrows(EditionNotFoundException.class, () ->
        {
            book1.getEditionById(1);
        });
    }

    @Test
    public void addEditionFromStringIfHasNoSuchEdition() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020, 0, 0");
        Edition expected = new Edition(new String[]{"1", "123", "2020"});

        assertEquals(1, book1.getEditions().size());
        assertTrue(book1.getEditions().contains(expected));
        assertTrue(expected.compare(book1.getEditionById(1)));
    }

    @Test
    public void addEditionFromStringIfHasSuchEditionThrowsObjectDuplicationException() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020, 0, 0");

        assertThrows(ObjectDuplicationException.class, () ->
        {
            book1.addEditionFromString("1, 123, 2020, 0, 0");
        });
        assertEquals(1, book1.getEditions().size());
    }

    @Test
    public void addEditionFromStringIfWrongInformationFormatThrowsException() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        assertThrows(Exception.class, () ->
        {
            book1.addEditionFromString("a, 123, 2020, 0, 0");
        });
    }

    @Test
    public void borrowIfPossible() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020, 1, 0");
        book1.borrow(1);

        assertEquals(1, book1.getEditionById(1).getBorrowed());
    }

    @Test
    public void borrowIfNotPossibleThrowsException() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020, 0, 0");

        assertThrows(Exception.class, () ->
        {
            book1.borrow(1);
        });
    }

    @Test
    public void compareIfSame() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        Book book2 = new Book(new String[]{"1", "title", "author", "2017"});

        assertTrue(book1.compare(book2));
    }

    @Test
    public void compareIfDifferent() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        Book book2 = new Book(new String[]{"1", "title2", "author", "2017"});

        assertFalse(book1.compare(book2));
    }

    @Test
    public void compareIfHaveDifferentEditions() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020");
        Book book2 = new Book(new String[]{"1", "title", "author", "2017"});

        assertFalse(book1.compare(book2));
    }

    @Test
    public void testPrint() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020");

        assertEquals("1, title, author, 2017, 1", book1.print());
    }

    @Test
    public void testToString() throws Exception {
        Book book1 = new Book(new String[]{"1", "title", "author", "2017"});
        book1.addEditionFromString("1, 123, 2020");
        Edition edition = new Edition(new String[]{"1", "123", "2020"});

        assertEquals("id 1 | title, author, 2017\n\teditions:\n\t" + edition.toString(), book1.toString());
    }
}