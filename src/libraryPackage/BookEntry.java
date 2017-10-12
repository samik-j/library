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

    public boolean compareEditions(Set<Edition> editions1, Set<Edition> editions2)
    {
        int counter = 0;
        if(editions1.equals(editions2)) {
            for (Edition edition1 : editions1) {
                for(Edition edition2 : editions2) {
                    if(edition1.compare(edition2))
                        counter++;
                }
            }
            if(counter == editions1.size())
                return true;
        }
        return false;
    }

    public boolean compare(BookEntry bookEntry) {
        return this.book.compare(bookEntry.book) && this.compareEditions(this.editions, bookEntry.editions);
    }

    @Override
    public String toString() {
        String bookEntryToString = "" + this.book + "\n\teditions:"; //z tym moze byc problem przy wpisywaniu z pliku to nie lepiej zrobic public void print?
        for(Edition edition : this.editions)
            bookEntryToString += "\n\t" + edition;
        return bookEntryToString;
    }

    @Override
    public int hashCode() { //musialam overrideowac bo mam hashset w catalogue. i wtedy testy te co sa do metod Set<BookEntry> nie dawaly dobrego wyniku w assertEquals
        return this.book.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntry bookEntry = (BookEntry) o;

        return book != null ? book.equals(bookEntry.book) : bookEntry.book == null;
    }

}
