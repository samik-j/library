package libraryPackage;

import java.io.*;
import java.util.*;

public class UserInterface {

    private final Library library;
    private final Scanner input;

    public UserInterface(Library _library, Scanner _input) {
        this.library = _library;
        this.input = _input;
    }

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(new Library(), new Scanner(System.in));

        userInterface.run();
    }

    public void run() {
        this.readCatalogueFromFile();
        int action = 0;
        do {
            action = this.getAction();
            runAction(action);
        } while(action != 0);
        this.printCatalogueToFile();
    }

    private int getAction() {
        System.out.println("" +
                "1 PRINT CATALOGUE\n" +
                //"2 ADD BOOK ENTRY\n" + //add book and editions
                "3 ADD BOOK\n" +
                "4 ADD EDITION\n" +
                "5 SEARCH BOOK\n" +
                "6 SEARCH EDITION\n" +
                "7 BORROW\n" +
                "8 ADD QUANTITY\n" +
                "0 EXIT");
        return input.nextInt();
    }

    private void runAction(int action) {
        switch(action) {
            case 1:
                this.printCatalogue();
                break;
            case 2:
                //this.addBookEntryToCatalogue();
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
            case 8:
                this.addQuantityToCatalogue();
            default:
                break;
        }
    }

    private void printCatalogue() {
        for(Map.Entry<Integer, Book> entry : library.getAllIdAnBooks().entrySet()) {
            System.out.println(entry.getValue());
        }
    }
/*
    private void addBookEntryToCatalogue() {
        System.out.println("id, title, author, originalPublicationDate, isbn, publicationDate");
        input.nextLine();
        try {
            this.library.addBookEntryFromString(input.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
*/
    private void addBookToCatalogue() {
        System.out.println("id, title, author, originalPublicationDate");
        input.nextLine();
        try {
            this.library.addBookFromString(input.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addEditionToCatalogue() {
        System.out.print("book id : ");
        final int id = input.nextInt();
        System.out.println("id, isbn, publicationDate | id, isbn, publicationDate, quantity, borrowed");
        input.nextLine();
        try {
            this.library.addEditionFromStringToBook(id, input.nextLine());
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
            System.out.println(this.library.getBookById(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void searchCatalogueByTitle() {
        System.out.println("title");
        input.nextLine();
        final String title = input.nextLine();
        try {
            Set<Book> found = this.library.getBookByTitle(title);
            if(found.isEmpty())
                System.out.println("Title not found");
            else
                this.printBooks(found);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchCatalogueByAuthor() {
        System.out.println("author");
        input.nextLine();
        final String author = input.nextLine();
        try {
            Set<Book> found = this.library.getBookByAuthor(author);
            if(found.isEmpty())
                System.out.println("Author not found");
            else
                this.printBooks(found);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchBookEditionInCatalogue() {
        System.out.println("isbn");
        input.nextLine();
        final String isbn = input.nextLine();
        try {
            System.out.println(this.library.getBookEditionByIsbn(isbn));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrowBook() {
        System.out.println("bookId.editionId");
        input.nextLine();
        final String id = input.nextLine();
        try {
            final String[] ids = id.split("\\.");
            this.library.borrowBook(Integer.parseInt(ids[0]), Integer.parseInt(ids[1]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void printBooks(Set<Book> books) {
        for(Book book : books)
            System.out.println(book);
    }

    private void addQuantityToCatalogue() {

    }

    private void readCatalogueFromFile() {
        try {
            this.library.readFromFile(getBufferedReaderForFile("library.txt"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static BufferedReader getBufferedReaderForFile(final String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("F:\\joanna\\java\\workspace\\library\\textFiles\\" + fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return reader;
    }

    private void printCatalogueToFile() {
        try {
            BufferedWriter writer = new BufferedWriter((new FileWriter("F:\\joanna\\java\\workspace\\library\\textFiles\\library.txt", false)));
            this.library.printToFile(writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
