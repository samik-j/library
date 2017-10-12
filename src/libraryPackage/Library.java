package libraryPackage;

import java.util.*;

public class Library {

    private final Catalogue catalogue;
    private final Scanner input;

    public Library(Catalogue _catalogue, Scanner _input) {
        this.catalogue = _catalogue;
        this.input = _input;
    }

    public static void main(String[] args) {
        Library library = new Library(new Catalogue(), new Scanner(System.in));

        library.run();
    }

    public void run() {
        int action = 0;
        do {
            action = this.getAction();
            runAction(action);
        } while(action != 0);
    }

    private int getAction() {
        System.out.println("" +
                "1 PRINT CATALOGUE\n" +
                "2 ADD BOOK ENTRY\n" +
                "3 ADD BOOK\n" +
                "4 ADD EDITION\n" +
                "5 SEARCH BOOK\n" +
                "6 SEARCH EDITION\n" +
                "7 BORROW\n" +
                "0 EXIT");
        return input.nextInt();
    }

    private void runAction(int action) {
        switch(action) {
            case 1:
                this.printCatalogue();
                break;
            case 2:
                this.addBookEntryToCatalogue();
                break;
            case 3:
                this.addBookToCatalogue();
                break;
            case 4:
                this.addEditionToCatalogue();
                break;
            case 5:
                this.searchBookInCatalogue();
                break;
            case 6:
                this.searchBookEditionInCatalogue();
                break;
            case 7:
                this.borrowBook();
                break;
            default:
                break;
        }
    }

    private void printCatalogue() {
        for(Map.Entry<Integer, BookEntry> entry : catalogue.getAllIdAnBookQuantities().entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private void addBookEntryToCatalogue() {
        System.out.println("id, title, author, originalPublicationDate, isbn, publicationDate");
        input.nextLine();
        try {
            this.catalogue.addBookEntryFromString(input.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addBookToCatalogue() {
        System.out.println("id, title, author, originalPublicationDate");
        input.nextLine();
        try {
            this.catalogue.addBookFromString(input.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addEditionToCatalogue() {
        System.out.println("id, isbn, publicationDate");
        input.nextLine();
        try {
            this.catalogue.addEditionFromString(input.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchBookInCatalogue() {
        System.out.println("search by id | title | author");
        Set<String> searchOptions = new HashSet<>(Arrays.asList("id", "title", "author"));
        String searchBy = "";
        do {
            searchBy = input.next();
            switch(searchBy) {
                case "id":
                    this.searchCatalogueById();
                    break;
                case "title":
                    this.searchCatalogueByTitle();
                    break;
                case "author":
                    this.searchCatalogueByAuthor();
                    break;
                default:
                    break;
            }
        } while(!searchOptions.contains(searchBy));
    }

    private void searchCatalogueById() {
        System.out.println("id");
        final int id = input.nextInt();
        try {
            System.out.println(this.catalogue.getBookEntryById(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void searchCatalogueByTitle() {
        System.out.println("title");
        input.nextLine();
        final String title = input.nextLine();
        try {
            //sprawdzic czy puste jak jest to napisac ze nie znalazlo
            this.printBookEntries(this.catalogue.getBookEntriesByTitle(title));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchCatalogueByAuthor() {
        System.out.println("author");
        input.nextLine();
        final String author = input.nextLine();
        try {
            this.printBookEntries(this.catalogue.getBookEntriesByAuthor(author));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchBookEditionInCatalogue() {
        System.out.println("isbn");
        input.nextLine();
        final String isbn = input.nextLine();
        try {
            System.out.println(this.catalogue.getBookEditionByIsbn(isbn));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrowBook() {
        System.out.println("id, isbn");
        final int id = input.nextInt();
        final String isbn = input.nextLine();
        try {
            this.catalogue.borrowBook(id, isbn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void printBookEntries(Set<BookEntry> bookEntries) {
        for(BookEntry bookEntry : bookEntries)
            System.out.println(bookEntry);
    }
}
