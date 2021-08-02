package validators;

public interface PersonValidator {
    boolean checkName(String name);
    boolean checkRoles(String roles);
    boolean checkEmail(String email);
    boolean checkPhoneNumbers(String phones);
}
