package libraryTestPackage;

import libraryPackage.Edition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditionTest {

    @Test
    public void addQuantity() {
        Edition edition = new Edition("123", "2000");
        edition.addQuantity(5);
        assertEquals(5, edition.getQuantity());
    }

    @Test
    public void getDefaultQuantity() {
        Edition edition = new Edition("123", "2000");
        assertEquals(0, edition.getQuantity());
    }

}