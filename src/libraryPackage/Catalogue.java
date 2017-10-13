package libraryPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class Catalogue {
    private Map<Integer, BookEntry> books;

    public Catalogue() {
        this.books = new HashMap<>();
    }

    public Map<Integer, BookEntry> getAllIdAnBookQuantities() {
        return this.books;
    }

    public BookEntry getBookEntryById(final int id) throws BookNotFoundException {
        if(this.books.containsKey(id))
            return this.books.get(id);
        else
            throw new BookNotFoundException("Id not found");
    }

    public Book getBookById(final int id) throws BookNotFoundException {
        return this.getBookEntryById(id).getBook();
    }

    public Set<BookEntry> getBookEntriesByTitle(final String title) {
        Set<BookEntry> found = new HashSet<>();
        for(BookEntry bookEntry : this.books.values()) {
            if(bookEntry.getBook().getTitle().contains(title)) {
                found.add(bookEntry);
            }
        }
        return found;
    }

    public Set<BookEntry> getBookEntriesByAuthor(final String author) {
        Set<BookEntry> found = new HashSet<>();
        for(BookEntry bookEntry : this.books.values()) {
            if(bookEntry.getBook().getAuthor().contains(author)) {
                found.add(bookEntry);
            }
        }
        return found;
    }

    public Set<Edition> getEditionsById(final int id) throws BookNotFoundException {
        return this.getBookEntryById(id).getEditions();
    }

    public BookEdition getBookEditionByIsbn(final String isbn) throws BookNotFoundException, EditionNotFoundException {
        for(BookEntry bookEntry : this.books.values()) {
            if(bookEntry.getEditions().contains(new Edition(isbn)))
                return new BookEdition(bookEntry.getBook(), bookEntry.getEditionByIsbn(isbn));
        }
        throw new BookNotFoundException("Edition not Found");
    }

    public void addBookEntryFromString(final String bookInformation) throws Exception {
        try {
            final String[] bookInfo = bookInformation.split(", ");
            final int id = Integer.parseInt(bookInfo[0]);
            final String title = bookInfo[1];
            final String author = bookInfo[2];
            final String originalPublicationYear = bookInfo[3];
            final String isbn = bookInfo[4];
            final String publicationYear = bookInfo[5];

            this.addBook(new Book(id, title, author, originalPublicationYear));
            this.getEditionsById(id).add(new Edition(isbn, publicationYear));
        } catch (ObjectDuplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    private Book createBookFromString(final String bookInformation) throws Exception {
        try {
            final String[] bookInfo = bookInformation.split(", ");
            final int id = Integer.parseInt(bookInfo[0]);
            final String title = bookInfo[1];
            final String author = bookInfo[2];
            final String originalPublicationYear = bookInfo[3];

            return new Book(id, title, author, originalPublicationYear);
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    public void addBookFromString(final String bookInformation) throws Exception {
        try {
            final String[] bookInfo = bookInformation.split(", ");
            final int id = Integer.parseInt(bookInfo[0]);
            final String title = bookInfo[1];
            final String author = bookInfo[2];
            final String originalPublicationYear = bookInfo[3];

            this.addBook(new Book(id, title, author, originalPublicationYear));
        } catch (ObjectDuplicationException e){
            throw e;
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    private void addBook(final Book book) throws ObjectDuplicationException {
        if(!this.books.containsKey(book.getId()))
            this.books.put(book.getId(), new BookEntry(book));
        else
            throw new ObjectDuplicationException("Id already exists");
    }

    public void addEditionFromString(final String editionInformation) throws Exception {
        try {
            final String[] editionInfo = editionInformation.split(", ");
            final int id = Integer.parseInt(editionInfo[0]);
            final String isbn = editionInfo[1];
            final String publicationYear = editionInfo[2];

            if(editionInfo.length == 3)
                this.addEdition(id, isbn, publicationYear); // czy addEdition(id, isbn, year, 0, 0 i sie pozbywam metody z (id, isbn, year)
            else {
                final int quantity = Integer.parseInt(editionInfo[3]);
                final int borrowed = Integer.parseInt(editionInfo[4]);
                this.addEdition(id, isbn, publicationYear, quantity, borrowed);
            }
        } catch (ObjectDuplicationException e) {
            throw e;
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    private void addEdition(final int id, final String isbn, final String publicationYear) throws BookNotFoundException, ObjectDuplicationException {
        if(this.books.containsKey(id)) {
            Edition edition = new Edition(isbn, publicationYear);

            if(!this.getEditionsById(id).contains(edition))
                this.getEditionsById(id).add(edition);
            else
                throw new ObjectDuplicationException("Edition already exists");
        }
        else
            throw new BookNotFoundException("Id not found");
    }

    //tak czy zrobic to jako addEditionFromFile i wyciagnac to z addEditionFromString? i wtedy addEditionFromFile przyjmuje String[] ktorego podzielilam w readFromFile?
    //czy robic addEdition(id, isbn, year) a potem .addQuantity i .addBorrowed?
    private void addEdition(final int id, final String isbn, final String publicationYear, final int quantity, final int borrowed) throws BookNotFoundException, ObjectDuplicationException {
        if(this.books.containsKey(id)) {
            Edition edition = new Edition(isbn, publicationYear, quantity, borrowed);

            if(!this.getEditionsById(id).contains(edition))
                this.getEditionsById(id).add(edition);
            else
                throw new ObjectDuplicationException("Edition already exists");
        }
        else
            throw new BookNotFoundException("Id not found");
    }

    public void borrowBook(final int id, final String isbn) throws Exception {
        if(!this.getBookEntryById(id).getEditionByIsbn(isbn).borrow())
            throw new Exception("Book not available");
    }

    public void printToFile(final BufferedWriter writer) throws IOException {
        try {
            for(BookEntry bookEntry : this.books.values()) {
                writer.write(bookEntry.print());
                writer.newLine();
                for(Edition edition : bookEntry.getEditions()) {
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

    public void addBooksFromFile(final BufferedReader reader) throws Exception {
        String currentBookLine = "";
        try {
            while((currentBookLine = reader.readLine()) != null) { //tak czy zrobic ze edycje zapisuje z id?
                this.addBookFromString(currentBookLine);
                final String[] info = currentBookLine.split(", ");
                final int editionsNumber = Integer.parseInt(info[info.length - 1]);
                for(int i = 0; i < editionsNumber; i++)
                    this.addEditionFromString(info[0] + ", " + reader.readLine());
            }

        } catch (IOException e) {
            throw new IOException("Error while reading file");
        } finally {
            tryCloseReader(reader);
        }
    }

    public void addBooksFromFilev2(final BufferedReader reader) throws Exception {
        String currentBookLine = "";
        try {
            while((currentBookLine = reader.readLine()) != null) {
                BookEntry newBookEntry = this.createNewBookEntry(currentBookLine);
                final String[] info = currentBookLine.split(", ");
                final int editionsNumber = Integer.parseInt(info[info.length - 1]);
                for(int i = 0; i < editionsNumber; i++){
                    newBookEntry.addEdition(new Edition(reader.readLine().split(", ")));
                }
            }
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        } finally {
            tryCloseReader(reader);
        }
    }

    private BookEntry createNewBookEntry(String currentBookLine) throws Exception {
        Book newBook = createBookFromString(currentBookLine);
        BookEntry newBookEntry = new BookEntry(newBook);
        books.put(newBook.getId(), newBookEntry);
        return newBookEntry;
    }

    private static void tryCloseReader(final BufferedReader reader) throws IOException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        }
    }
}
