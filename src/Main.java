import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        updatePassword("java_skypro_go1", "D_1hWiKjjP_9", "D_1hWiKjjP_9");
    }

    static void updatePassword(String login, String password, String confirmPassword) {
        try {
            checkLogin(login);
            checkPassword(password);
            matchPasswords(password, confirmPassword);
        } catch (WrongLoginException exception) {
            System.out.println(exception.getMessage() + " Логин должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        } catch (WrongPasswordException exception) {
            System.out.println(exception.getMessage() + " Пароль должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        }
    }

    static boolean checkErrorValidation(String item) {
        Pattern pattern = Pattern.compile("(\\d[a-zA-Z]|[a-zA-Z]\\d)+?");
        Matcher matcher = pattern.matcher(item);

        return item.isEmpty() || item.length() > 20 || !matcher.find() || item.contains("-");
    }

    static void checkLogin(String login) {
        boolean noValid = checkErrorValidation(login);

        if (noValid) {
            throw new WrongLoginException("Логин не валидный!");
        }
    }

    static void checkPassword(String password) {
        boolean noValid = checkErrorValidation(password);

        if (noValid) {
            throw new WrongPasswordException("Пароль не валидный!");
        }
    }

    static void matchPasswords(String password, String confirmPassword) {
        boolean isMatches = password.equals(confirmPassword);

        if (!isMatches) {
            throw new WrongPasswordException("Пароли не совпадают!");
        }
    }
}