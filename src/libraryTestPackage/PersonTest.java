package libraryTestPackage;

import libraryPackage.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void testConstructor() throws Exception {
        Person person = new Person(new String[]{"1", "name", "2010-11-20"});

        assertEquals(1, person.getId());
        assertEquals("name", person.getName());
        assertEquals("2010-11-20", person.getSignUpDate());
    }

    @Test
    public void testConstructorThrowsException() {
        assertThrows(Exception.class, () ->
        {
            new Person(new String[]{"a", "name", "2010-11-20"});
        });
    }

    @Test
    public void compareIfSame() throws Exception {
        Person person1 = new Person(new String[]{"1", "name", "2010-11-20"});
        Person person2 = new Person(new String[]{"1", "name", "2010-11-20"});

        assertTrue(person1.compare(person2));
    }

    @Test
    public void compareIfDifferentName() throws Exception {
        Person person1 = new Person(new String[]{"1", "name", "2010-11-20"});
        Person person2 = new Person(new String[]{"1", "name2", "2010-11-20"});

        assertFalse(person1.compare(person2));
    }

    @Test
    public void compareIfDifferentId() throws Exception {
        Person person1 = new Person(new String[]{"1", "name", "2010-11-20"});
        Person person2 = new Person(new String[]{"2", "name", "2010-11-20"});

        assertFalse(person1.compare(person2));
    }

    @Test
    public void compareIfDifferentDate() throws Exception {
        Person person1 = new Person(new String[]{"1", "name", "2010-11-20"});
        Person person2 = new Person(new String[]{"1", "name", "2010-10-20"});

        assertFalse(person1.compare(person2));
    }

    @Test
    public void testPrint() throws Exception {
        Person person = new Person(new String[]{"1", "name", "2010-11-20"});
        String expected = "1, name, 2010-11-20";

        assertEquals(expected, person.print());
    }



}