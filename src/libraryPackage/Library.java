package libraryPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class Library {
    private Map<Integer, Book> catalogue;

    public Library() {
        this.catalogue = new HashMap<>();
    }

    public Map<Integer, Book> getCatalogue() { // wierd name
        return this.catalogue;
    }

    public Book getBookById(final int id) {
        return this.catalogue.get(id);
    }

    private Book getBookByIdThrowable(final int id) throws BookNotFoundException {
        if (this.catalogue.containsKey(id))
            return this.catalogue.get(id);
        else
            throw new BookNotFoundException("Id not found");
    }

    public Set<Book> getBooksByTitle(final String title) {
        Set<Book> found = new HashSet<>();
        for (Book book : this.catalogue.values()) {
            if (book.getTitle().contains(title)) {
                found.add(book);
            }
        }
        return found;
    }

    public Set<Book> getBooksByAuthor(final String author) {
        Set<Book> found = new HashSet<>();
        for (Book book : this.catalogue.values()) {
            if (book.getAuthor().contains(author)) {
                found.add(book);
            }
        }
        return found;
    }

    public BookEdition getBookEditionByIsbn(final String isbn) throws EditionNotFoundException {
        for (Book book : this.catalogue.values()) {
            if (book.hasEditionByIsbn(isbn))
                return new BookEdition(book, book.getEditionByIsbn(isbn));
        }
        throw new EditionNotFoundException("Edition not Found");
    }

    private Book createBookFromString(final String bookInformation) throws Exception {
        return new Book(bookInformation.split(", "));
    }

    private void addBook(final Book book) throws ObjectDuplicationException {
        if (!this.catalogue.containsKey(book.getId()))
            this.catalogue.put(book.getId(), book);
        else
            throw new ObjectDuplicationException("Id already exists");
    }

    public void addBookFromString(final String bookInformation) throws Exception {
        this.addBook(createBookFromString(bookInformation));
    }

    public void addEditionFromStringToBook(final int id, final String editionInformation) throws Exception {
        this.getBookByIdThrowable(id).addEditionFromString(editionInformation);
    }

    public void borrowBook(final int bookId, final int editionId) throws Exception {
        this.getBookByIdThrowable(bookId).borrow(editionId);
    }

    public void printToFile(final BufferedWriter writer) throws IOException {
        try {
            for (Book book : this.catalogue.values()) {
                writer.write(book.print());
                writer.newLine();
                for (Edition edition : book.getEditions()) {
                    writer.write(edition.print());
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Error while writing to file");
        }
    }


    public void readFromFile(final BufferedReader reader) throws Exception {
        this.catalogue.clear();
        this.addBooksFromFile(reader);
    }

    public void addBooksFromFile(final BufferedReader reader) throws Exception { // create filehandler class to read nad write from file
        String currentBookLine = "";
        try {
            while ((currentBookLine = reader.readLine()) != null) {
                Book newBook = this.createBookFromString(currentBookLine);
                this.addBook(newBook);
                final String[] info = currentBookLine.split(", ");
                final int editionsCount = Integer.parseInt(info[info.length - 1]);
                for (int i = 0; i < editionsCount; i++) {
                    newBook.addEditionFromString(reader.readLine());
                }
            }
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        } finally {
            tryCloseReader(reader);
        }
    }

    private static void tryCloseReader(final BufferedReader reader) throws IOException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        }
    }
}
