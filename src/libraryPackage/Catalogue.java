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

    public BookEntry getBookEntryById(final int id) throws BookNotFoundException {
        if(this.books.containsKey(id))
            return this.books.get(id);
        else
            throw new BookNotFoundException("Id not found");
    }

    public Book getBookById(final int id) throws BookNotFoundException {
        return this.getBookEntryById(id).getBook();
    }

    public Set<BookEntry> getBookEntriesByTitle(final String title) throws BookNotFoundException {
        Set<BookEntry> found = new HashSet<>();
        for(BookEntry bookEntry : this.books.values()) {
            if(bookEntry.getBook().getTitle().contains(title)) {
                found.add(bookEntry);
            }
        }
        if(found.isEmpty())
            throw new BookNotFoundException("Title not found");
        return found;
    }

    public Set<BookEntry> getBookEntriesByAuthor(final String author) throws BookNotFoundException {
        Set<BookEntry> found = new HashSet<>();
        for(BookEntry bookEntry : this.books.values()) {
            if(bookEntry.getBook().getAuthor().contains(author)) {
                found.add(bookEntry);
            }
        }
        if(found.isEmpty())
            throw new BookNotFoundException("Author not found");
        return found;
    }

    public Set<Edition> getEditionsById(final int id) throws BookNotFoundException {
        return this.getBookEntryById(id).getEditions();
    }

    public BookEdition getBookEditionByIsbn(final String isbn) throws BookNotFoundException {
        for(BookEntry bookEntry : this.books.values()) {
            if(bookEntry.getEditions().contains(new Edition(isbn, "date")))
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

            this.tryToAddEdition(id, isbn, publicationYear);
        } catch (ObjectDuplicationException e) {
            throw e;
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    private void tryToAddEdition(final int id, final String isbn, final String publicationYear) throws BookNotFoundException, ObjectDuplicationException {
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
