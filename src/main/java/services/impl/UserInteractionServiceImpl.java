package services.impl;

import beans.Person;
import beans.Role;
import services.interfaces.UserInteractionService;
import services.interfaces.Validation;
import utils.Transformation;

import java.util.List;
import java.util.Scanner;

public class InputServiceScImpl implements UserInteractionService {
    private Validation val = ValidatorImpl.getInstance();

    private static volatile InputServiceScImpl instance;
    private static final Object lock = new Object();

    public static InputServiceScImpl getInstance() {
        if (instance == null)
            synchronized (lock) {
                if (instance == null)
                    instance = new InputServiceScImpl();
            }
        return instance;
    }

    private InputServiceScImpl() {
    }

    public String name(Scanner sc){
        String name = sc.next();
        while (!val.checkName(name)){
            System.out.println("Error - Enter a name in Latin with a capital letter");
                        name = sc.next();
        }
        return name;
    }

    public String lastName(Scanner sc){
        String lastName = sc.next();
        while (!val.checkName(lastName)){
            System.out.println("Error - Enter a last name in Latin with a capital letter");
            lastName = sc.next();
        }
        return lastName;
    }

    public List<Role> roles(Scanner sc){
        String roles = sc.nextLine();
        while (!val.checkRoles(roles)) {
            System.out.println("Error - Enter roles separated by a space(USER, CUSTOMER, ADMIN, PROVIDER, SUPER_ADMIN):");
            roles = sc.nextLine();
        }
        return Transformation.getListRole(roles);
    }

    public String email(Scanner sc){
        String email = sc.next();
        while (!val.checkEmail(email)) {
            System.out.println("Error - email in the form *****@*****.*");
            email = sc.next();
        }
        return email;
    }

    public List<String> phones(Scanner sc){
        String phones = sc.nextLine();
        while (!val.checkPhoneNumbers(phones)) {
            System.out.println("Error - phones must be in the form 375 *****");
            phones = sc.nextLine();
        }
        return Transformation.getListPhones(phones);
    }

    public Person indexPerson(List<Person> personList, Scanner sc){
        int indexPerson = sc.nextInt();
        while (!(indexPerson < personList.size())) {
            System.out.println("Error - Invalid user number");
            indexPerson = sc.nextInt();
        }
        return personList.get(indexPerson);
    }

    public int intBorder(int min, int max, Scanner sc){
        int updateNumber = sc.nextInt();
        while (updateNumber > max || updateNumber < min) {
            updateNumber = sc.nextInt();
        }
        return updateNumber;
    }

}
