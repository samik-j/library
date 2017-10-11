package libraryPackage;

import java.util.Map;
import java.util.Scanner;

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
        System.out.println("1 print catalogue\n" +
                "2 add Book\n" +
                "0 exit");
        return input.nextInt();
    }

    private void runAction(int action) {
        switch (action) {
            case 1:
                this.printCatalogue();
                break;
            case 2:
                this.addBookEntryToCatalogue();
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
        input.nextLine();
        try {
            this.catalogue.addBookEntryFromString(input.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
