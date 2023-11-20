package co.uptc.edu.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*** 
 *@author FelipeL
    *  this class is used to validate actions and attributes of the user class in the controllers.
 ***/
public class UserUtilities {
    public boolean validPassword(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
