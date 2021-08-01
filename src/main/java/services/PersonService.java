package services;

import beans.Person;
import interfaces.PersonCrudTxt;
import services.impl.PersonCrudTxtImpl;

public class PersonService {
    private PersonCrudTxt crudTxt = PersonCrudTxtImpl.getInstance();

    private static volatile PersonService instance;
    private static final Object lock = new Object();

    public static PersonService getInstance() {
        if (instance == null)
            synchronized (lock) {
                if (instance == null)
                    instance = new PersonService();
            }
        return instance;
    }

    private PersonService() {
    }

    public void add(Person person){
        crudTxt.create(person);
    }
    public void update(String person, String newPerson){
        crudTxt.update(person, newPerson);
    }
    public void delete(Person person){
        crudTxt.delete(person.toString());
    }
    public String read(){
        return crudTxt.read();
    }


}
