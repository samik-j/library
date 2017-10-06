package libraryTestPackage;

import libraryPackage.Book;
import libraryPackage.BookQuantity;
import libraryPackage.Catalogue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {
    Catalogue catalogue = new Catalogue();

    @Test
    public void defaultConstructorReturnsEmptyCatalogue() {
        assertTrue(this.catalogue.getAllIdAnBookQuantities().isEmpty());
    }

    @Test
    public void addBookFromStringIfCorrectFormat() throws Exception {
        BookQuantity expected = new BookQuantity(new Book(1, "123", "title", "author", "2017"), 1);
        this.catalogue.addBookFromString("1, 123, title, author, 2017, 1");
        //assertEquals(expected, this.catalogue.getBookQuantityById(1)); // compare a nie equals? ale czy jest az taka roznica? czy compare jest tylko do testow?
        assertTrue(expected.compare(this.catalogue.getBookQuantityById(1)));
    }

    @Test
    public void addBookFromStringIfIncorrectFormat() throws Exception {
        assertThrows(Exception.class, () ->
        {
            this.catalogue.addBookFromString("1, 123, title, author, 2012, f");
        });
    }

    @Test
    public void testGetAllIdAndBookQuantity() {
        catalogue.getAllIdAnBookQuantities();
    }

}