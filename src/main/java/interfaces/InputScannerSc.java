package interfaces;

import beans.Person;
import beans.Role;

import java.util.List;
import java.util.Scanner;

public interface InputScannerSc {
    String name(Scanner sc);
    String lastName(Scanner sc);
    List<Role> roles(Scanner sc);
    String email(Scanner sc);
    List<String> phones(Scanner sc);
    Person indexPerson(List<Person> personList, Scanner sc);
    int intBorder(int min, int max, Scanner sc);

}
