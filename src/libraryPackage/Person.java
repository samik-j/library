package libraryPackage;

import java.time.LocalDate;

public class Person {
    private final int id;
    private String name;
    private String signUpDate;

    public Person(String[] info) throws Exception {
        try {
            this.id = Integer.parseInt(info[0]);
            this.name = info[1];
            this.signUpDate = info[2];
        } catch (Exception e) {
            throw new Exception("Wrong information format");
        }
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSignUpDate() {
        return this.signUpDate;
    }

    public String print() {
        return "" + this.id + ", " + this.name + ", " + this.signUpDate;
    }

    public boolean compare(Person other) {
        return this.id == other.id && this.name.equals(other.name) && this.signUpDate.equals(other.signUpDate);
    }

    @Override
    public String toString() {
        return "id " + this.id + " | " + this.name + ", " + this.signUpDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id == person.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}