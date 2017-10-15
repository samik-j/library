package libraryTestPackage;

import libraryPackage.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;


class LibraryTest {

    Library library;
    FileHandler fileHandler = mock(FileHandler.class);

    @Test
    public void testReadCatalogueWithEmptyFile() throws Exception {
        when(fileHandler.readCatalogue()).thenReturn(new HashMap<>());
        library = new Library(fileHandler);

        assertTrue(library.getCatalogue().isEmpty());
    }

    @Test
    public void testReadCatalogue() throws Exception {
        Map<Integer, Book> catalogue = new HashMap<>();
        catalogue.put(1, new Book(new String[]{"1", "title", "author", "date"}));
        when(fileHandler.readCatalogue()).thenReturn(catalogue);
        library = new Library(fileHandler);

        assertEquals(1, library.getCatalogue().size());
    }
}