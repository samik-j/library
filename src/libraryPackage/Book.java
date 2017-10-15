package libraryPackage;

import java.util.HashSet;
import java.util.Set;

public class Book {
    private final int id;
    private String title;
    private String author;
    private String originalPublicationYear;
    private Set<Edition> editions;

    public Book(final String[] info) throws Exception {
        try {
            this.id = Integer.parseInt(info[0]);
            this.title = info[1];
            this.author = info[2];
            this.originalPublicationYear = info[3];
            this.editions = new HashSet<>();
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getId() {
        return this.id;
    }

    public String getOriginalPublicationYear() {
        return this.originalPublicationYear;
    }

    public Set<Edition> getEditions() {
        return this.editions;
    }

    public boolean hasEditionByIsbn(final String isbn) {
        for(Edition edition : this.editions) {
            if(edition.getIsbn().equals(isbn))
                return true;
        }
        return false;
    }

    public Edition getEditionByIsbn(final String isbn) throws EditionNotFoundException {
        for(Edition edition : this.editions) {
            if(edition.getIsbn().equals(isbn))
                return edition;
        }
        throw new EditionNotFoundException("Edition ISBN not found");
    }

    public Edition getEditionById(final int id) throws EditionNotFoundException {
        for(Edition edition : this.editions) {
            if(edition.getId() == id)
                return edition;
        }
        throw new EditionNotFoundException("Edition Id not found");
    }

    public void addEditionFromString(final String editionInformation) throws Exception {
            this.addEdition(createEditionFromString(editionInformation));
    }

    private Edition createEditionFromString(final String editionInformation) throws Exception {
        return new Edition(editionInformation.split(", "));
    }

    private void addEdition(final Edition edition) throws ObjectDuplicationException {
        if(!this.editions.contains(edition))
            this.editions.add(edition);
        else
            throw new ObjectDuplicationException("Edition already exists");
    }

    public void borrow(final int id) throws Exception {
        if(!this.getEditionById(id).borrow())
            throw new Exception("Book not available");
    }

    public boolean compare(final Book book) {
        return this.id == book.id && this.title.equals(book.title)
                && this.author.equals(book.author) &&
                this.originalPublicationYear.equals(book.originalPublicationYear) &&
                this.editions.equals(book.editions);
    }

    public String print() {
        return  "" + this.id + ", " + this.title + ", " + this.author + ", " + this.originalPublicationYear + ", " + this.editions.size();
    }

    @Override
    public String toString() {
        String bookToString = "id " + this.id + " | " + this.title + ", " + this.author + ", " + this.originalPublicationYear +
                "\n\teditions:";
        for(Edition edition : this.editions)
            bookToString += "\n\t" + edition;
        return bookToString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}