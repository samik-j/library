package libraryPackage;

import java.util.HashSet;
import java.util.Set;

public class BookEntry {
    private final Book book;
    private final Set<Edition> editions;

    public BookEntry(Book _book) {
        this.book = _book;
        this.editions = new HashSet<>();
    }

    public void addEdition(final Edition edition) {
        editions.add(edition);
    }

    public Book getBook() {
        return this.book;
    }

    public Set<Edition> getEditions() {
        return this.editions;
    }

    public Edition getEditionByIsbn(final String isbn) {
        for(Edition edition : editions) {
            if(edition.getIsbn().equals(isbn))
                return edition;
        }
        return null;
    }

    boolean compareEditions(Edition e1, Edition e2)
    {
        return false;
    }

    public boolean compare(BookEntry bookEntry) {
        return this.book.compare(bookEntry.book) && this.editions.equals(bookEntry.editions);
    }

    @Override
    public String toString() {
        String bookEntryToString = "" + this.book + "\n\teditions:";
        for(Edition edition : this.editions)
            bookEntryToString += "\n\t" + edition;
        return bookEntryToString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntry bookEntry = (BookEntry) o;

        return book != null ? book.equals(bookEntry.book) : bookEntry.book == null;
    }

}
