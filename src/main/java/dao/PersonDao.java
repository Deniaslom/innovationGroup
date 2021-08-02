package services.interfaces;

import beans.Person;

public interface PersonDao {
    void create(Person person);
    String read();
    void update(String str, String update);
    void delete(String delete);
}
