package services.impl;

import beans.Person;
import dao.PersonDao;
import dao.impl.TxtPersonDaoImpl;
import services.PersonService;

public class PersonServiceImpl implements PersonService {
    private PersonDao personDao = TxtPersonDaoImpl.getInstance();

    private static volatile PersonServiceImpl instance;
    private static final Object lock = new Object();

    public static PersonServiceImpl getInstance() {
        if (instance == null)
            synchronized (lock) {
                if (instance == null)
                    instance = new PersonServiceImpl();
            }
        return instance;
    }

    private PersonServiceImpl() {
    }
    @Override
    public void add(Person person){
        personDao.create(person);
    }
    @Override
    public void update(String person, String newPerson){
        personDao.update(person, newPerson);
    }
    @Override
    public void delete(Person person){
        personDao.delete(person.toString());
    }
    @Override
    public String read(){
        return personDao.read();
    }


}
