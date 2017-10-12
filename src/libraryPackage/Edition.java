package libraryPackage;

public class Edition {
    private final String isbn;
    private String publicationYear;
    private int quantity;
    private int borrowed;

    public Edition(String _isbn, String _publicationYear) {
        this.isbn = _isbn;
        this.quantity = 0;
        this.publicationYear = _publicationYear;
        this.borrowed = 0;
    }

    public void addQuantity(final int quantity) { // add remove :)
        this.quantity += quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public boolean borrow() {
        if(this.quantity > this.borrowed) {
            ++this.borrowed;
            return true;
        }
        return false;
    }

    public int getBorrowed() {
        return this.borrowed;
    }

    public boolean compare(Edition edition) {
        return this.isbn.equals(edition.isbn) &&
                this.publicationYear.equals(edition.publicationYear) &&
                this.quantity == edition.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edition edition = (Edition) o;

        return isbn != null ? isbn.equals(edition.isbn) : edition.isbn == null;
    }

    @Override
    public String toString() {
        return "" + isbn + ", " + publicationYear + ", " + quantity;
    }

    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }
}