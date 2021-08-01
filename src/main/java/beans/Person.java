package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private String last_name;
    private List<Role> role = new ArrayList<>();
    private String email;
    private List<String> phoneNumbers;

    public Person() {

    }

    public Person(String name, String last_name, String email, List<Role> role, List<String> phoneNumbers) {
        this.name = name;
        this.last_name = last_name;
        this.role = role;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumbers() {
        return phoneNumbers.stream().collect(Collectors.joining(", "));
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person сlient = (Person) o;
        return Objects.equals(name, сlient.name) &&
                Objects.equals(role, сlient.role) &&
                Objects.equals(email, сlient.email) &&
                Objects.equals(phoneNumbers, сlient.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role, email, phoneNumbers);
    }

    @Override
    public String toString() {
        return  name + " " +
                last_name + " " +
                email + " " +
                role + " " +
                getPhoneNumbers();
    }
}
