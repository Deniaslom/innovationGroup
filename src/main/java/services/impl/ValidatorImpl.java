package services.impl;

import beans.Role;
import interfaces.Validation;

import java.util.ArrayList;
import java.util.List;

public class ValidatorImpl implements Validation {
    private static volatile ValidatorImpl instance;
    private static final Object lock = new Object();

    public static ValidatorImpl getInstance() {
        if (instance == null)
            synchronized (lock) {
                if (instance == null)
                    instance = new ValidatorImpl();
            }
        return instance;
    }

    private ValidatorImpl() {
    }

    public boolean checkName(String name) {
        return ((name != null) && name.matches("[A-Z][a-z]+"));
    }

    public boolean checkRoles(String roles) {
        boolean validation = false;
        String[] rolesMassiv = roles.trim().split(" ");
        System.out.println("roles: " + roles);
        List<Role> personRoles = new ArrayList<>();
        for (String role : rolesMassiv) {
            try {
                System.out.println(role);
                personRoles.add(Role.valueOf(role));
            } catch (IllegalArgumentException e) {
                System.out.println("Такой роли не существует");
            }

        }

        if (personRoles.size() == 2) {
            int sym = personRoles.get(0).getLevel() + personRoles.get(1).getLevel();
            if (sym == 3) {
                validation = true;
            } else {
                System.out.println("сочетание таких ролей запрещено");
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
