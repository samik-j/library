package libraryPackage;

import java.util.*;

public class Catalogue {
    private Map<Integer, BookEntry> books;

    public Catalogue() {
        this.books = new HashMap<>();
    }

    public Map<Integer, BookEntry> getAllIdAnBookQuantities() {
        return this.books;
    }

    public BookEntry getBookEntryById(final int id) throws BookNotFoundException { //tu dodalam ze moze byc exception
        if(this.books.containsKey(id))
            return this.books.get(id);
        else
            throw new BookNotFoundException("Id not found");
    }

    public Set<Edition> getEditionsById(final int id) throws BookNotFoundException { // czy w tych dwoch tez robic if i throw czy nie?
        return this.getBookEntryById(id).getEditions();
    }

    public Book getBookById(final int id) throws BookNotFoundException { // czy w tych dwoch tez robic if i throw czy nie?
            return this.getBookEntryById(id).getBook();
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
            this.addEdition(id, isbn, publicationYear);
        } catch (ObjectDuplicationException e) {
            throw e;
        } catch (BookNotFoundException e) { //nigdy nie bedzie tego exception bo dodaje edition do book ktory tez dodaje. to usunac??
            throw e;
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

            this.addEdition(id, isbn, publicationYear);
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
}
