package co.uptc.edu.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtilities {
    public boolean validPassword(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
