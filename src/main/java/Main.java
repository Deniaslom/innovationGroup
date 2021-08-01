import beans.Person;
import beans.Role;
import interfaces.Validation;
import services.InputService;
import services.PersonService;
import services.impl.ValidatorImpl;
import utils.Transformation;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PersonService personService = PersonService.getInstance();
        InputService input = InputService.getInstance();

        Scanner in = new Scanner(System.in);

        List<Role> roleList;
        List<String> phonesList;
        List<Person> personList;

        Person person;
        boolean flag = true;
        String name;
        String last_name;
        String email;
        String oldPerson;

        while (flag) {
            System.out.println("\n1. создание");
            System.out.println("2. редактирования");
            System.out.println("3. просмотра пользователей");
            System.out.println("4. выход");

            System.out.print("Введите число: ");
            String num = in.next();

            switch (num) {
                case ("1"):
                    System.out.println("===Создание объекта===");

                    System.out.println("Введите имя:");
                    name = input.name(in);

                    System.out.println("Введите фамилию:");
                    last_name = input.lastName(in);


                    System.out.println("Введите роли через пробел(USER, CUSTOMER, ADMIN, PROVIDER, SUPER_ADMIN):");
                    roleList = input.roles(in);

                    System.out.println("Введите email:");
                    email = input.email(in);

                    System.out.println("Введите номер телефона(ов):");
                    phonesList = input.phones(in);

                    personService.add(new Person(name, last_name, email, roleList, phonesList));
                    break;
                case ("2"):
                    personList = Transformation.getListPersons(personService.read());
                    Transformation.indexedListOfPeople(personService.read());

                    System.out.println("Какой редактировать?");
                    person = input.indexPerson(personList, in);

                    System.out.println("=================");
                    System.out.println(person.toString());
                    System.out.println("Какое поле редактировать?");
                    System.out.println("1. name");
                    System.out.println("2. last_name");
                    System.out.println("3. roles");
                    System.out.println("4. email");
                    System.out.println("5. phoneNumbers");
                    System.out.println("6. delete person");
                    int updateNumber = input.intBorder(1,6, in);
                    switch (updateNumber) {
                        case (1):
                            System.out.println("Введите имя:");
                            String nameUpdate = input.name(in);
                            oldPerson = person.toString();
                            person.setName(nameUpdate);

                            personService.update(oldPerson, person.toString());
                            break;
                        case (2):
                            System.out.println("Введите last_name:");
                            last_name = input.lastName(in);
                            oldPerson = person.toString();
                            person.setLast_name(last_name);

                            personService.update(oldPerson, person.toString());
                            break;
                        case (3):
                            System.out.println("Введите роли через пробел(USER, CUSTOMER, ADMIN, PROVIDER, SUPER_ADMIN):");
                            roleList = input.roles(in);
                            oldPerson = person.toString();
                            person.setRole(roleList);

                            personService.update(oldPerson, person.toString());
                            break;
                        case (4):
                            System.out.println("Введите email:");
                            email = input.email(in);
                            oldPerson = person.toString();
                            person.setEmail(email);

                            personService.update(oldPerson, person.toString());
                            break;
                        case (5):
                            System.out.println("Введите номер телефона(ов):");
                            phonesList = input.phones(in);
                            oldPerson = person.toString();
                            person.setPhoneNumbers(phonesList);

                            personService.update(oldPerson, person.toString());
                            break;
                        case(6):
                            personService.delete(person);
                            break;
                    }

                    break;
                case ("3"):
                    Transformation.indexedListOfPeople(personService.read());
                    break;
                case ("4"):
                    flag = false;
                    break;
                default:
                    System.out.println(num);
                    System.out.println("неправильный выбор");
                    break;
            }
        }

        in.close();


    }
}