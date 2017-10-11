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

    public boolean compare(BookEntry bookEntry) {
        return this.book.compare(bookEntry.book) && this.editions.equals(bookEntry.editions); //to jest niepelne compare bo nie moge zrobic compare na editions
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntry bookEntry = (BookEntry) o;

        return book != null ? book.equals(bookEntry.book) : bookEntry.book == null; // chrck quantity //ale teraz nie mam quantity to co mam set sprawdzac? czy zrobic compare
    }

}
