package libraryPackage;

public class Person {
    private final int id;
    private String name;

    public Person(String[] info) throws Exception {
        try {
            this.id = Integer.parseInt(info[0]);
            this.name = info[1];
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

    public String print() {
        return "" + this.id + ", " + this.name;
    }

    public boolean compare(Person other) {
        return this.id == other.id && this.name.equals(other.name);
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
