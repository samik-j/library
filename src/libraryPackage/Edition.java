package libraryPackage;

public class Edition {
    private final String isbn;
    private String publicationYear;
    private int quantity;
    private int borrowed;

    public Edition(String _isbn, String _publicationYear, int _quantity, int _borrowed) {
        this.isbn = _isbn;
        this.publicationYear = _publicationYear;
        this.quantity = _quantity;
        this.borrowed = _borrowed;
    }

    public Edition(final String[] editionInformation) throws Exception {
        try {
            this.isbn = editionInformation[0];
            this.publicationYear = editionInformation[1];
            this.quantity = Integer.parseInt(editionInformation[2]);
            this.borrowed = Integer.parseInt(editionInformation[3]);
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    public Edition(String _isbn, String _publicationYear) {
        this.isbn = _isbn;
        this.publicationYear = _publicationYear;
        this.quantity = 0;
        this.borrowed = 0;
    }

    public Edition(String _isbn) {
        this.isbn = _isbn;
        this.publicationYear = "notKnown";
        this.quantity = 0;
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
                this.quantity == edition.quantity &&
                this.borrowed == edition.borrowed;
    }

    public String print() {
        return "" + this.isbn + ", " +this.publicationYear + ", " + this.quantity + ", " + this.borrowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edition edition = (Edition) o;

        return this.isbn != null ? this.isbn.equals(edition.isbn) : edition.isbn == null;
    }

    @Override
    public String toString() {
        return "" + this.isbn + ", " + this.publicationYear + ", quantity: " + this.quantity + ", borrowed: " + this.borrowed;
    }

    @Override
    public int hashCode() {
        return this.isbn != null ? this.isbn.hashCode() : 0;
    }
}