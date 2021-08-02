package services;

import beans.Person;

public interface PersonService {
    void add(Person person);

    void update(String person, String newPerson);

    void delete(Person person);

    String read();
}
