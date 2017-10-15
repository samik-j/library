package libraryTestPackage;

import libraryPackage.Edition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditionTest {

    @Test
    public void testSimpleConstructor() throws Exception {
        Edition e = new Edition(new String[]{"1", "isbn", "date", "20", "1"});

        assertEquals(e.getQuantity(), 20);
        assertEquals(e.getId(), 1);
        assertEquals(e.getIsbn(), "isbn");
        assertEquals(e.getPublicationYear(), "date");
        assertEquals(e.getBorrowed(), 1);
    }

    @Test
    public void testWHenConstructorThrowsException() {
        assertThrows(Exception.class, () ->
        {
            new Edition(new String[]{"a", "isbn", "date", "20", "1"});
        });
    }

    @Test
    public void addQuantity() throws Exception {
        Edition edition = new Edition(new String[]{"1", "123", "2000", "20", "1"});
        edition.addQuantity(2);

        assertEquals(22, edition.getQuantity());
    }

    @Test
    public void borrowIfPossibleReturnsTrue() throws Exception {
        Edition edition = new Edition(new String[]{"1", "123", "2000", "20", "1"});

        assertTrue(edition.borrow());
        assertEquals(2, edition.getBorrowed());
    }

    @Test
    public void borrowIfQuantityEqualsZeroReturnsFalse() throws Exception {
        Edition edition = new Edition(new String[]{"1", "123", "2000", "0", "0"});

        assertFalse(edition.borrow());
        assertEquals(0, edition.getBorrowed());
    }

    @Test
    public void borrowIfQuantityIsLessOrEqualBorrowedReturnsFalse() throws Exception {
        Edition edition = new Edition(new String[]{"1", "123", "2000", "1", "1"});

        assertFalse(edition.borrow());
        assertEquals(1, edition.getBorrowed());
    }

    @Test
    public void compareIfSameReturnsTrue() throws Exception {
        Edition edition1 = new Edition(new String[]{"1", "123", "2000", "1", "1"});
        Edition edition2 = new Edition(new String[]{"1", "123", "2000", "1", "1"});

        assertTrue(edition1.compare(edition2));
    }

    @Test
    public void compareIfDifferentReturnsFalse() throws Exception {
        Edition edition1 = new Edition(new String[]{"1", "123", "2000", "1", "1"});
        Edition edition2 = new Edition(new String[]{"1", "123", "2002", "1", "1"});

        assertFalse(edition1.compare(edition2));
    }

    @Test
    public void testPrint() throws Exception {
        Edition edition = new Edition(new String[]{"1", "123", "2000", "1", "1"});

        assertEquals("1, 123, 2000, 1, 1", edition.print());
    }

    @Test
    public void testToString() throws Exception {
        Edition edition = new Edition(new String[]{"1", "123", "2000", "1", "1"});

        assertEquals("id 1 | 123, 2000, quantity: 1, borrowed: 1", edition.toString());
    }
}