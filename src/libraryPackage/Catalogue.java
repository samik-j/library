package libraryPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class Catalogue {
    private Map<Integer, Book> books;

    public Catalogue() {
        this.books = new HashMap<>();
    }

    public Map<Integer, Book> getAllIdAnBooks() { // wierd name
        return this.books;
    }

    public Book getBookById(final int id) throws BookNotFoundException {
        if(this.books.containsKey(id))
            return this.books.get(id);
        else
            throw new BookNotFoundException("Id not found");
    }

    public Set<Book> getBookByTitle(final String title) {
        Set<Book> found = new HashSet<>();
        for(Book book : this.books.values()) {
            if(book.getTitle().contains(title)) {
                found.add(book);
            }
        }
        return found;
    }

    public Set<Book> getBookByAuthor(final String author) {
        Set<Book> found = new HashSet<>();
        for(Book book : this.books.values()) {
            if(book.getAuthor().contains(author)) {
                found.add(book);
            }
        }
        return found;
    }

    public Set<Edition> getEditionsById(final int id) throws BookNotFoundException { // getEditionsByBookId
        return this.getBookById(id).getEditions();
    }

    public BookEdition getBookEditionByIsbn(final String isbn) throws EditionNotFoundException {
        for(Book book : this.books.values()) {
            if(book.getEditions().contains(new Edition(isbn)))
                return new BookEdition(book, book.getEditionByIsbn(isbn));
        }
        throw new EditionNotFoundException("Edition not Found");
    }

    private Book createBookFromString(final String bookInformation) throws Exception {
        try {
            final String[] bookInfo = bookInformation.split(", ");
            return new Book(bookInfo); // return new Book(bookInformation.split(", "))
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    private void addBook(final Book book) throws ObjectDuplicationException {
        if(!this.books.containsKey(book.getId()))
            this.books.put(book.getId(), book);
        else
            throw new ObjectDuplicationException("Id already exists");
    }

    public void addBookFromString(final String bookInformation) throws Exception {
            this.addBook(createBookFromString(bookInformation));
    }

    public void addEditionFromStringToBook(final int id, final String editionInformation) throws Exception {
        this.getBookById(id).addEditionFromString(editionInformation);
    }

    public void borrowBook(final String id) throws Exception {
        final String[] ids = id.split("\\."); // move up(method which calls it)
        this.getBookById(Integer.parseInt(ids[0])).borrow(Integer.parseInt(ids[1]));
    }

    public void printToFile(final BufferedWriter writer) throws IOException {
        try {
            for(Book book : this.books.values()) {
                writer.write(book.print());
                writer.newLine();
                for(Edition edition : book.getEditions()) {
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
        this.books.clear();
        this.addBooksFromFile(reader);
    }

    public void addBooksFromFile(final BufferedReader reader) throws Exception { // create filehandler class to read nad write from file
        String currentBookLine = "";
        try {
            while((currentBookLine = reader.readLine()) != null) {
                Book newBook = this.createBookFromString(currentBookLine);
                this.addBook(newBook);
                final String[] info = currentBookLine.split(", ");
                final int editionsNumber = Integer.parseInt(info[info.length - 1]);
                for(int i = 0; i < editionsNumber; i++){
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
