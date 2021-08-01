package utils;

import beans.Person;
import beans.Role;
import services.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transformation {


    public static List<String> listPhones(String phones){
        List<String> phoneList = new ArrayList<>();
        String[] strings = phones.split(" ");
        for(String s : strings){
            phoneList.add(s);
        }
        return phoneList;
    }

    public static List<Person> getListPersons(String stringPersons){
        List<Person> persons = new ArrayList<>();
        String[] masPersons = stringPersons.split("\n");
        for(String personString : masPersons){
            Person person = new Person();
            String[] fields = personString.split(" ");
            person.setName(fields[0]);
            person.setLast_name(fields[1]);
            person.setEmail(fields[2]);
            person.setRole(getListRole(personString));
            person.setPhoneNumbers(getListPhones(personString));
            persons.add(person);
        }



        return persons;
    }

    public static List<Role> getListRole(String stringPerson){
        List<Role> roleList = new ArrayList<>();
        String[] rolesSplit = stringPerson.split(" ");
        Pattern pattern = Pattern.compile("([A-Z]+[_]*[A-Z]+)");
        for(String role : rolesSplit) {
            Matcher matcher = pattern.matcher(role);
            if (matcher.find()) {
                roleList.add(Role.valueOf(matcher.group(0)));
            }
        }
        return roleList;
    }

    public static List<String> getListPhones(String stringPerson){
        List<String> phonesList = new ArrayList<>();
        String[] phonesSplit = stringPerson.split(" ");
        Pattern pattern = Pattern.compile("((375)(\\d{9}))");
        for(String phone : phonesSplit) {
            Matcher matcher = pattern.matcher(phone);
            if (matcher.find()) {
                phonesList.add(matcher.group(0));
            }
        }
        return phonesList;
    }

    public static void indexedListOfPeople(String persons){
        PersonService personService = PersonService.getInstance();
        List<Person> personList = Transformation.getListPersons(personService.read());
        for(int i = 0; i < personList.size(); i++){
            System.out.println(i + ". " + personList.get(i));
        }
    }
}
