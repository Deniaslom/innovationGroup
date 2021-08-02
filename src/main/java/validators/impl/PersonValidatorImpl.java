package services.impl;

import beans.Role;
import services.interfaces.PersonValidation;

import java.util.ArrayList;
import java.util.List;

public class PersonValidatorImpl implements PersonValidation {
    private static volatile PersonValidatorImpl instance;
    private static final Object lock = new Object();

    public static PersonValidatorImpl getInstance() {
        if (instance == null)
            synchronized (lock) {
                if (instance == null)
                    instance = new PersonValidatorImpl();
            }
        return instance;
    }

    private PersonValidatorImpl() {
    }

    public boolean checkName(String name) {
        return ((name != null) && name.matches("[A-Z][a-z]+"));
    }

    public boolean checkRoles(String roles) {
        boolean validation = false;
        String[] rolesMassiv = roles.trim().split(" ");
        List<Role> personRoles = new ArrayList<>();
        for (String role : rolesMassiv) {
            try {
                personRoles.add(Role.valueOf(role));
            } catch (IllegalArgumentException e) {
                System.out.println("There is no such role");
            }

        }

        if (personRoles.size() == 2) {
            int sym = personRoles.stream().mapToInt(x -> x.getLevel()).sum();
            if (sym == 3) {
                validation = true;
            } else {
                System.out.println("the combination of such roles is prohibited");
            }
        } else {
            if (personRoles.size() == 1) {
                validation = true;
            }
        }

        return validation;
    }

    public boolean checkEmail(String email) {
        return email.matches("(([A-Za-z0-9]+)([@][A-Za-z0-9]+[.][A-Za-z0-9]+))");
    }

    public boolean checkPhoneNumbers(String phonesStr) {
        boolean validation = true;
        String[] phones = phonesStr.trim().split(" ");
        if (phones.length > 0 && phones.length < 4) {
            for (String phone : phones) {
                if (!phone.trim().matches("((375)(\\d{9}))")) {
                    validation = false;
                }
            }
        }else{
            validation = false;
        }
        return validation;
    }
}
