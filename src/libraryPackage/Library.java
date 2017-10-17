package libraryPackage;

import java.io.IOException;
import java.util.*;

public class Library {
    private final FileHandler fileHandler;
    private final Map<Integer, Book> catalogue;
    private final Map<Integer, Person> members;

    public Library(FileHandler _fileHandler) throws Exception {
        this.fileHandler  = _fileHandler;
        this.catalogue = this.fileHandler.readCatalogue();
        this.members = this.fileHandler.readMembers();
    }

    public Map<Integer, Book> getCatalogue() {
        return this.catalogue;
    }

    public Map<Integer, Person> getMembers() {
        return this.members;
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

    public void addBookFromString(final String bookInformation) throws Exception {
        this.addBook(createBookFromString(bookInformation));
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

    public void addEditionFromStringToBook(final int id, final String editionInformation) throws Exception {
        this.getBookByIdThrowable(id).addEditionFromString(editionInformation);
    }

    public void addPersonFromString(final String person) throws Exception {
        this.addPerson(createPersonFromString(person));
    }

    private Person createPersonFromString(final String personInformation) throws Exception {
        return new Person(personInformation.split(", "));
    }

    private void addPerson(final Person person) throws ObjectDuplicationException {
        if(!this.members.containsKey(person.getId()))
            this.members.put(person.getId(), person);
        else
            throw new ObjectDuplicationException("Id already exists");
    }

    public void borrowBook(final int bookId, final int editionId) throws Exception {
        this.getBookByIdThrowable(bookId).borrow(editionId);
    }

    public void printCatalogue() {
        for(Map.Entry<Integer, Book> entry : this.catalogue.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void saveLibrary() throws IOException {
        this.saveCatalogue();
        this.saveMembers();
    }

    private void saveCatalogue() throws IOException {
        this.fileHandler.saveCatalogue(this.catalogue);
    }

    private void saveMembers() throws IOException {
        this.fileHandler.saveMembers(this.members);
    }
}
