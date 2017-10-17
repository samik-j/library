package libraryTestPackage;

import libraryPackage.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void testConstructor() throws Exception {
        Person person = new Person(new String[]{"1", "name"});

        assertEquals(1, person.getId());
        assertEquals("name", person.getName());
    }

    @Test
    public void testConstructorThrowsException() {
        assertThrows(Exception.class, () ->
        {
            new Person(new String[]{"a", "name"});
        });
    }

    @Test
    public void compareIfSame() throws Exception {
        Person person1 = new Person(new String[]{"1", "name"});
        Person person2 = new Person(new String[]{"1", "name"});

        assertTrue(person1.compare(person2));
    }

    @Test
    public void compareIfDifferent() throws Exception {
        Person person1 = new Person(new String[]{"1", "name"});
        Person person2 = new Person(new String[]{"1", "name2"});

        assertFalse(person1.compare(person2));
    }

    @Test
    public void testPrint() throws Exception {
        Person person = new Person(new String[]{"1", "name"});
        String expected = "1, name";

        assertEquals(expected, person.print());
    }



}