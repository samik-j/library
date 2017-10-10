package libraryPackage;

import java.util.Set;

public class BookEntry {
    private Book book;
    private int quantity ;
    private Set<Edition> editions;

    public BookEntry(Book _book, Set<Edition> _editions) { //czy editions nie powinno byc w konstruktorze?
        this.book = _book;
        this.quantity = 0;
        this.editions = _editions;
    }

    public void addEdition(final Edition edition) {
        editions.add(edition);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntry bookEntry = (BookEntry) o;

        return book != null ? book.equals(bookEntry.book) : bookEntry.book == null;
    }

}
