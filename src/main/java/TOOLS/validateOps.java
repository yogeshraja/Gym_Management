package TOOLS;

import java.util.regex.Pattern;

public class validateOps {

    public static boolean validateEmail(String email) {
        String regex = "^[\\w[.-]]+[@][a-z]+([.][a-z0-9][a-z0-9]*)+$";

        return Pattern.matches(regex, email);
    }

    public static boolean validatePassword(String Pass) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[*@#&$!^])(?=\\S+$).{8,24}$";
        return Pattern.matches(regex, Pass);
    }

    public static boolean validateNumber(String Number) {
        String regex = "[\\d]{10,12}";
        return Pattern.matches(regex, Number);
    }

    public static boolean validateAge(String Age) {
        int a;
        try {
            a = Integer.parseInt(Age);
            if (a < 150) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validateForm(Object values[]) {
        return validateEmail((String) values[4]) && validatePassword((String) values[11]) && validateNumber((String) values[5]) && validateAge((String) values[2]);
    }

}
