package services;

import beans.Person;
import beans.Role;
import interfaces.Validation;
import services.impl.ValidatorImpl;
import utils.Transformation;

import java.util.List;
import java.util.Scanner;

public class InputService {
    private Validation val = ValidatorImpl.getInstance();

    private static volatile InputService instance;
    private static final Object lock = new Object();

    public static InputService getInstance() {
        if (instance == null)
            synchronized (lock) {
                if (instance == null)
                    instance = new InputService();
            }
        return instance;
    }

    private InputService() {
    }

    public String name(Scanner sc){
        String name = sc.next();
        while (!val.checkName(name)){
            System.out.println("Ошибка - Введите имя на латинице с большой буквы");
                        name = sc.next();
        }
        return name;
    }

    public String lastName(Scanner sc){
        String lastName = sc.next();
        while (!val.checkName(lastName)){
            System.out.println("Ошибка - Введите lastName на латинице с большой буквы");
            lastName = sc.next();
        }
        return lastName;
    }

    public List<Role> roles(Scanner sc){
        String roles = sc.nextLine();
        while (!val.checkRoles(roles)) {
            System.out.println("Ошибка - Введите роли через пробел(USER, CUSTOMER, ADMIN, PROVIDER, SUPER_ADMIN):");
            roles = sc.nextLine();
        }
        return Transformation.getListRole(roles);
    }

    public String email(Scanner sc){
        String email = sc.next();
        while (!val.checkEmail(email)) {
            System.out.println("Ошибка - Введите емэйл с @ b точкой");
            email = sc.next();
        }
        return email;
    }

    public List<String> phones(Scanner sc){
        String phones = sc.nextLine();
        while (!val.checkPhoneNumbers(phones)) {
            System.out.println("Ошибка - Введите номера телефонов");
            phones = sc.nextLine();
        }
        return Transformation.getListPhones(phones);
    }

    public Person indexPerson(List<Person> personList, Scanner sc){
        int indexPerson = sc.nextInt();
        while (!(indexPerson < personList.size())) {
            System.out.println("Ошибка - Неверный номер пользователя");
            indexPerson = sc.nextInt();
        }
        return personList.get(indexPerson);
    }

    public int intBorder(int min, int max, Scanner sc){
        int updateNumber = sc.nextInt();
        while (updateNumber > max || updateNumber < min) {
            System.out.println("Сделайте выбор от 1 до 5");
            updateNumber = sc.nextInt();
        }
        return updateNumber;
    }

}
