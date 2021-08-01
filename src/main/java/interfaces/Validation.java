package interfaces;

public interface Validation {
    boolean checkName(String name);
    boolean checkRoles(String roles);
    boolean checkEmail(String email);
    boolean checkPhoneNumbers(String phones);
}
