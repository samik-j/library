package libraryPackage;

import java.util.*;

public class Catalogue {
    private Map<Integer, BookEntry> books;

    public Catalogue() {
        this.books = new HashMap<>();
    }

    public void addFromString(String book) throws Exception {// usunac to cale
        try{
            final String[] bookInformation = book.split(", ");
            if(bookInformation.length ==6)
                this.addBookEntryFromString(bookInformation);
            else if(bookInformation.length == 3)
                this.addEditionFromString(bookInformation);
            else if(bookInformation.length ==4)
                this.addBookFromString(bookInformation);
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw e; //bo throwuje te exceptiony z poszczegolnych funkcji, czy zrobic throw new exception("wrong information format") ?
        }
    }

    public Map<Integer, BookEntry> getAllIdAnBookQuantities() {
        return this.books;
    }

    public BookEntry getBookEntryById(final int id) {
        return this.books.get(id);
    }

    public Set<Edition> getEditionsById(final int id) {
        return this.getBookEntryById(id).getEditions();
    }

    private void addBook(final Book book) {
        if(!this.books.containsKey(book.getId()))
            this.books.put(book.getId(), new BookEntry(book, new HashSet<>()));
        //throw exception ze juz jest takie id
    }

    private void addBookFromString(final String[] bookInfo) throws Exception {
        try {
            final int id = Integer.parseInt(bookInfo[0]);
            final String title = bookInfo[1];
            final String author = bookInfo[2];
            final String originalPublicationDate = bookInfo[3];
            this.addBook(new Book(id, title, author, originalPublicationDate));
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    private void addEdition(final int id, final String isbn, final String publicationDate) throws BookNotFoundException {
        if(this.books.containsKey(id)) {
            Edition edition = new Edition(isbn, publicationDate);
            if(!this.getEditionsById(id).contains(edition))
                this.getEditionsById(id).add(edition);
            //jak jest juz edycja to exception
        }
        else
            throw new BookNotFoundException("Book with id " + id + " does not exist");
    }

    private void addEditionFromString(final String[] editionInfo) throws Exception {
        try {
            final int id = Integer.parseInt(editionInfo[0]);
            final String isbn = editionInfo[1];
            final String publicationDate = editionInfo[2];
            this.addEdition(id, isbn, publicationDate);
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    private void addBookEntryFromString(final String[] bookInfo) throws Exception {
        try {
            final int id = Integer.parseInt(bookInfo[0]);
            final String title = bookInfo[1];
            final String author = bookInfo[2];
            final String originalPublicationDate = bookInfo[3];
            final String isbn = bookInfo[4];
            final String publicationDate = bookInfo[5];

            this.addBook(new Book(id, title, author, originalPublicationDate));
            this.addEdition(id, isbn, publicationDate);
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

}
