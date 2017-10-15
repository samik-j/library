package libraryTestPackage;

import libraryPackage.*;

class LibraryTest {
    Library library = new Library();
/*
    @Test
    public void defaultConstructorReturnsEmptyCatalogue() {
        assertTrue(this.library.getAllIdAnBookQuantities().isEmpty());
    }

    @Test
    public void addBookFromStringIfHasNoSuchBook() throws Exception {
        this.library.addBookFromString("1, title, author, 2000");
        Book expected = new Book(1, "title", "author", "2000");

        assertTrue(expected.compare(this.library.getBookById(1)));
    }

    @Test
    public void addBookFromStringIfHasSuchBookThrowsObjectDuplicationException() throws Exception {
        this.library.addBookFromString("1, title, author, 2000");

        assertThrows(ObjectDuplicationException.class, () ->
        {
            this.library.addBookFromString("1, title2, author, 2000");
        });
    }

    @Test
    public void addBookFromStringIfWrongInformationFormatThrowsException() {
        assertThrows(Exception.class, () ->
        {
            this.library.addBookFromString("a, title, author, 2000");
        });
    }

    @Test
    public void addEditionFromStringIfHasNoSuchEdition() throws Exception {
        this.library.addBookFromString("1, title, author, 2000");
        this.library.addEditionFromString("1, 123, 2020");
        Edition expected = new Edition("123", "2020");

        assertTrue(expected.compare(this.library.getBookEntryById(1).getEditionByIsbn("123")));
    }

    @Test
    public void addEditionFromStringIfHasSuchEditionThrowsObjectDuplicationException () throws Exception {
        this.library.addBookFromString("1, title, author, 2000");
        this.library.addEditionFromString("1, 123, 2020");

        assertThrows(ObjectDuplicationException.class, () ->
        {
            this.library.addEditionFromString("1, 123, 2020");
        });
    }

    @Test
    public void addEditionFromStringIfWrongIdThrowsBookNotFoundException () throws Exception {
        this.library.addBookFromString("1, title, author, 2000");

        assertThrows(BookNotFoundException.class, () ->
        {
            this.library.addEditionFromString("2, 123, 2020");
        });
    }

    @Test
    public void addEditionFromStringIfWrongInformationFormatThrowsException() throws Exception {
        this.library.addBookFromString("1, title, author, 2000");

        assertThrows(Exception.class, () ->
        {
            this.library.addEditionFromString("s, 123, 2020");
        });
    }

    @Test
    public void addBookEntryFromStringIfHasNoSuchBookEntry() throws Exception {
        this.library.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        BookEntry expected = new BookEntry(new Book(1, "title", "author", "2000"));
        expected.addEdition(new Edition("123", "2020"));

        assertTrue(expected.compare(this.library.getBookEntryById(1)));
    }

    @Test
    public void addBookEntryFromStringIfHasSuchBookEntryThrowsObjectDuplicationException() throws Exception {
        this.library.addBookFromString("1, title, author, 2000");

        assertThrows(ObjectDuplicationException.class, () ->
        {
            this.library.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        });
    }

    @Test
    public void addBookEntryFromStringIfWrongInformationFormatThrowsException() {
        assertThrows(Exception.class, () ->
        {
            this.library.addBookEntryFromString("s, title, author, 2000, 123, 2020");
        });
    }

    @Test
    public void getBookEntryByIdIfHasSuch() throws Exception {
        this.library.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        BookEntry expected = new BookEntry(new Book(1, "title", "author", "2000"));
        expected.addEdition(new Edition("123", "2020"));

        assertTrue(expected.compare(this.library.getBookEntryById(1)));
    }

    @Test
    public void getBookEntryByIdIfHasNotSuch() throws Exception {
        assertThrows(BookNotFoundException.class, () ->
        {
            this.library.getBookEntryById(1);
        });
    }

    @Test
    public void getBookEditionByIsbnIfHasSuch() throws Exception {
        this.library.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        BookEdition expected = new BookEdition(new Book(1, "title", "author", "2000"), new Edition("123", "2020"));

        assertTrue(expected.compare(this.library.getBookEditionByIsbn("123")));
    }

    @Test
    public void getBookEditionByIsbnIfHasNoSuch() throws Exception {
        this.library.addBookEntryFromString("1, title, author, 2000, 123, 2020");

        assertThrows(BookNotFoundException.class, () ->
        {
            this.library.getBookEditionByIsbn("124");
        });
    }

    @Test
    public void getBookEntriesByTitleIfHasSuch() throws Exception {
        this.library.addBookEntryFromString("1, title, author, 2000, 123, 2020");
        this.library.addBookEntryFromString("2, title2, author, 2000, 123, 2020");
        this.library.addBookEntryFromString("3, tytul, author, 2000, 123, 2020");
        BookEntry expected1 = new BookEntry(new Book(1, "title", "author", "2000"));
        expected1.addEdition(new Edition("123", "2020"));
        BookEntry expected2 = new BookEntry(new Book(2, "title2", "author", "2000"));
        expected2.addEdition(new Edition("123", "2020"));
        Set<BookEntry> expected = new HashSet<>();
        expected.add(expected1);
        expected.add(expected2);

        assertEquals(expected, this.library.getBookEntriesByTitle("titl"));
    }

    @Test
    public void getBookEntriesByTitleIfHasNoSuch() {
        assertTrue(this.library.getBookEntriesByTitle("title").isEmpty());
    }

    @Test
    public void getBookEntriesByAuthorIfHasSuch() throws Exception {
        this.library.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        this.library.addBookEntryFromString("2, title2, name2 lastName, 2000, 123, 2020");
        this.library.addBookEntryFromString("3, title, author, 2000, 123, 2020");
        BookEntry expected1 = new BookEntry(new Book(1, "title", "name1 lastName", "2000"));
        expected1.addEdition(new Edition("123", "2020"));
        BookEntry expected2 = new BookEntry(new Book(2, "title2", "name2 lastName", "2000"));
        expected2.addEdition(new Edition("123", "2020"));
        Set<BookEntry> expected = new HashSet<>();
        expected.add(expected1);
        expected.add(expected2);

        assertTrue(expected.equals(this.library.getBookEntriesByAuthor("lastName")));
    }

    @Test
    public void getBookEntriesByAuthorIfHasNoSuch() {
        assertTrue(this.library.getBookEntriesByAuthor("author").isEmpty());
    }

    @Test
    public void borrowBookIfAvailable() throws Exception {
        this.library.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        this.library.getBookEntryById(1).getEditionByIsbn("123").addQuantity(1);
        this.library.borrowBook(1, "123");
        assertEquals(1, this.library.getBookEntryById(1).getEditionByIsbn("123").getBorrowed());
    }

    @Test
    public void borrowBookIfUnavailable() throws Exception {
        this.library.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        assertThrows(Exception.class, () ->
        {
            this.library.borrowBook(1, "123");
        });
    }

    @Test
    public void borrowBookIfWrongId() throws Exception {
        this.library.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        assertThrows(BookNotFoundException.class, () ->
        {
            this.library.borrowBook(2, "123");
        });
    }

    @Test
    public void borrowBookIfWrongEdition() throws Exception {
        this.library.addBookEntryFromString("1, title, name1 lastName, 2000, 123, 2020");
        assertThrows(EditionNotFoundException.class, () ->
        {
            this.library.borrowBook(1, "1232");
        });
    }
    */
}