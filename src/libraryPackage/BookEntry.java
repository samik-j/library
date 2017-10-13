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

    public Edition getEditionByIsbn(final String isbn) throws EditionNotFoundException {
        for(Edition edition : editions) {
            if(edition.getIsbn().equals(isbn))
                return edition;
        }
        throw new EditionNotFoundException("Edition not found");
    }

    public boolean compareEditions(Set<Edition> otherEditions)
    {
        int counter = 0;
        if(this.editions.equals(otherEditions)) {
            for (Edition edition : this.editions) {
                for(Edition otherEdition : otherEditions) {
                    if(edition.compare(otherEdition))
                        counter++;
                }
            }
            if(counter == this.editions.size())
                return true;
        }
        return false;
    }

    public boolean compare(BookEntry bookEntry) {
        return this.book.compare(bookEntry.book) && this.compareEditions(bookEntry.editions);
    }

    public String print() {
        return this.book.print() + ", " + this.editions.size();
    }

    @Override
    public String toString() {
        String bookEntryToString = "" + this.book + "\n\teditions:";
        for(Edition edition : this.editions)
            bookEntryToString += "\n\t" + edition;
        return bookEntryToString;
    }

    @Override
    public int hashCode() {
        return this.book.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntry bookEntry = (BookEntry) o;

        return this.book != null ? this.book.equals(bookEntry.book) : bookEntry.book == null;
    }

}
