package libraryPackage;

public class Edition {
    private final int id;
    private final String isbn;
    private String publicationYear;
    private int quantity;
    private int borrowed;

    public Edition(final String[] info) throws Exception {
        try {
            this.id = Integer.parseInt(info[0]);
            this.isbn = info[1];
            this.publicationYear = info[2];

            if (info.length == 3) {
                this.quantity = 0;
                this.borrowed = 0;
            }
            else if (info.length == 5) {
                this.quantity = Integer.parseInt(info[3]);
                this.borrowed = Integer.parseInt(info[4]);
            }
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    public int getId() {
        return this.id;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getPublicationYear() {
        return this.publicationYear;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getBorrowed() {
        return this.borrowed;
    }

    public void addQuantity(final int quantity) { // add remove :)
        this.quantity += quantity;
    }

    public boolean borrow() {
        if (this.quantity > this.borrowed) {
            ++this.borrowed;
            return true;
        }
        return false;
    }

    public boolean compare(Edition edition) {
        return this.id == edition.id &&
                this.isbn.equals(edition.isbn) &&
                this.publicationYear.equals(edition.publicationYear) &&
                this.quantity == edition.quantity &&
                this.borrowed == edition.borrowed;
    }

    public String print() {
        return "" + this.id + ", " + this.isbn + ", " + this.publicationYear + ", " + this.quantity + ", " + this.borrowed;
    }

    @Override
    public String toString() {
        return "id " + this.id + " | " + this.isbn + ", " + this.publicationYear + ", quantity: " + this.quantity + ", borrowed: " + this.borrowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edition edition = (Edition) o;

        return id == edition.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}