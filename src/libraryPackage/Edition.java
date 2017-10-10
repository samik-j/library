package libraryPackage;

public class Edition {
    private final String isbn;
    private String publicationDate;
    private int quantity;

    public Edition(String _isbn, String _publicationDate) {
        this.isbn = _isbn;
        this.quantity = 0;
        this.publicationDate = _publicationDate;
    }

    public void addQuantity(final int quantity) {
        this.quantity += quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getIsbn() {
        return this.isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edition edition = (Edition) o;

        return isbn != null ? isbn.equals(edition.isbn) : edition.isbn == null;
    }

    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }
}