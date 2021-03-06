package com.epsilon.utils;

import java.util.regex.Pattern;

/**
 * Created by Dandoh on 2/14/16.
 */
public class Validator {

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{6,15}$";
    private static Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);


    public static boolean isUsernameValid(String username) {
        return username != null && usernamePattern.matcher(username).matches();

    }


    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isEmailValid(String email) {
        return email != null && emailPattern.matcher(email).matches();

    }


    private static final String PHONE_PATTERN = "^[0-9]{9,14}$";

    private static Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    public static boolean isPhoneValid(String phone) {
        return phone != null && phonePattern.matcher(phone).matches();

    }


    private static final String PASSWORD_PATTERN = "^[a-z0-9]{4,15}$";
    private static Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);


    public static boolean isPasswordValid(String password) {
        return password != null && passwordPattern.matcher(password).matches();

    }

    private static final String NAME_PATTERN = "^[a-zA-Z]{1,15}$";

    private static Pattern namePattern = Pattern.compile(NAME_PATTERN);

    public static boolean isNameValid(String name) {
        return name != null && namePattern.matcher(name).matches();
    }
}
