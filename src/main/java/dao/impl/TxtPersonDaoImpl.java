package dao.impl;

import beans.Person;
import dao.PersonDao;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TxtPersonDaoImpl implements PersonDao {
    private static volatile TxtPersonDaoImpl instance;
    private static final Logger LOGGER = Logger.getLogger(TxtPersonDaoImpl.class);

    public static TxtPersonDaoImpl getInstance() {
        if (instance == null)
            instance = new TxtPersonDaoImpl();

        return instance;
    }

    private TxtPersonDaoImpl() {
    }

    @Override
    public void create(Person person) {
        try (FileWriter writer = new FileWriter("persons.txt", true)) {
            writer.write(person.toString() + "\n");
            writer.flush();
        } catch (IOException e) {
            LOGGER.info("method create", e);
        }
    }

    @Override
    public String read() {
        StringBuilder str = new StringBuilder();
        try (FileReader reader = new FileReader("persons.txt")) {
            int c;

            while ((c = reader.read()) != -1) {
                str.append((char) c);
            }
        } catch (IOException ex) {
            LOGGER.info("method read", ex);
        }
        return str.toString();
    }

    @Override
    public void update(String oldPerson, String newPerson) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("persons.txt"));
            for (int i = 0; lines.size() > i; i++) {
                if (lines.get(i).equals(oldPerson)) {
                    lines.set(i, newPerson);
                }
            }
            Files.write(Paths.get("persons.txt"), lines);
        } catch (IOException e) {
            LOGGER.info("method update", e);
        }
    }

    @Override
    public void delete(String delete) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("persons.txt"));
            lines.remove(delete);
            Files.write(Paths.get("persons.txt"), lines);
        } catch (IOException e) {
            LOGGER.info("method delete", e);
        }

    }
}
