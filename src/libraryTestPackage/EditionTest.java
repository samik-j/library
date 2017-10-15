package libraryTestPackage;

import libraryPackage.Edition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditionTest {
/*
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

    @Test
    public void compareIfSame() {
        Edition edition1 = new Edition("123", "2000");
        Edition edition2 = new Edition("123", "2000");
        assertTrue(edition1.compare(edition2));
    }

    @Test
    public void compareIfDifferent() {
        Edition edition1 = new Edition("123", "2000");
        Edition edition2 = new Edition("123", "2001");
        assertFalse(edition1.compare(edition2));
    }

    @Test
    public void compareIfDifferentQuantity() {
        Edition edition1 = new Edition("123", "2000", 0, 0);
        Edition edition2 = new Edition("123", "2000", 1, 0);
        assertFalse(edition1.compare(edition2));
    }

    @Test
    public void testBorrowIfQuantityIsZero() {
        Edition edition1 = new Edition("123", "2000");
        assertFalse(edition1.borrow());
        assertEquals(0, edition1.getBorrowed());
    }

    @Test
    public void testBorrowIfQuantityIsMoreThanBorrowed() {
        Edition edition1 = new Edition("123", "2000");
        edition1.addQuantity(1);
        assertTrue(edition1.borrow());
        assertEquals(1, edition1.getBorrowed());
    }

    @Test
    public void testBorrowIfQuantityIsLessThanBorrowed() {
        Edition edition1 = new Edition("123", "2000");
        edition1.addQuantity(1);
        edition1.borrow();
        assertFalse(edition1.borrow());
        assertEquals(1, edition1.getBorrowed());
    }

    @Test
    public void testToString() {
        Edition edition1 = new Edition("123", "2000");
        String expected = "123, 2000, quantity: 0, borrowed: 0";
        assertEquals(expected, edition1.toString());
    }

    @Test
    public void testPrint() {
        Edition edition1 = new Edition("123", "2000");
        String expected = "123, 2000, 0, 0";
        assertEquals(expected, edition1.print());
    }
    */
}