package libraryPackage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    private final static String catalogueFile = "F:\\joanna\\java\\workspace\\library\\textFiles\\catalogue.txt";
    private final static String membersFile = "F:\\joanna\\java\\workspace\\library\\textFiles\\members.txt";

    public Map<Integer, Book> readCatalogue() throws Exception {
        final Map<Integer, Book> catalogue = new HashMap<>();
        final BufferedReader reader = getBufferedReaderForFile(catalogueFile);
        String currentBookLine = "";

        try {
            while((currentBookLine = reader.readLine()) != null) {
                final String[] info = currentBookLine.split(", ");
                final Book newBook = new Book(info);
                final int editionsCount = Integer.parseInt(info[info.length - 1]);

                catalogue.put(newBook.getId(), newBook);
                for(int i = 0; i < editionsCount; ++i)
                    newBook.addEditionFromString(reader.readLine());
            }
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        } catch (Exception e) {
            throw e;
        } finally {
            tryCloseReader(reader);
        }
        return catalogue;
    }

    public void saveCatalogue(final Map<Integer, Book> catalogue) throws IOException {
        try {
            final BufferedWriter writer = new BufferedWriter((new FileWriter(catalogueFile, false)));

            for (Book book : catalogue.values()) {
                writer.write(book.print());
                writer.newLine();
                for (Edition edition : book.getEditions()) {
                    writer.write(edition.print());
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Error while writing to file");
        }
    }

    public Map<Integer, Person> readMembers() throws Exception {
        final Map<Integer, Person> members = new HashMap<>();
        final BufferedReader reader = getBufferedReaderForFile(membersFile);
        String currentLine = "";

        try {
            while ((currentLine = reader.readLine()) != null) {
                String[] info = currentLine.split(", ");
                Person person = new Person(info);

                members.put(person.getId(), person);
            }
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        } catch (Exception e) {
            throw e;
        } finally {
            tryCloseReader(reader);
        }
        return members;
    }

    public void saveMembers(Map<Integer, Person> members) throws IOException {
        try {
            final BufferedWriter writer = new BufferedWriter((new FileWriter(membersFile, false)));

            for (Person person : members.values()) {
                writer.write(person.print());
                writer.newLine();
                }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Error while writing to file");
        }
    }

    private static BufferedReader getBufferedReaderForFile(final String file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return reader;
    }

    private static void tryCloseReader(final BufferedReader reader) throws IOException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new IOException("Error while reading file");
        }
    }
}
