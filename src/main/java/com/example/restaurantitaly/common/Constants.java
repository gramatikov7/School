package com.example.restaurantitaly.common;

public final class Constants {

    //Error message
    public static final String USERNAME_NOT_FOUND = "Username not found";
    public static final String DUPLICATE_USERNAME = "Username already exist";
    public static final String DUPLICATE_EMAIL = "Email already exist";

    //Validation
    public static final String WRONG_EMAIL = "Incorrect email.";
    public static final String EMAIL_PATTERN_STRING ="^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$)|(@\\[?((25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$)";
    public static final String NULL_EMAIL_MESSAGE = "Email cannot be null.";
    public static final String EMPTY_EMAIL_MESSAGE = "Email cannot be empty.";
    public static final String EMPTY_ALREADY_EXIST = "Email already exist";

    public static final String WRONG_USERNAME_LENGTH_MESSAGE = "Username must be between 5 and 20 characters long.";
    public static final String NULL_USERNAME_MESSAGE = "Username cannot be null.";
    public static final String EMPTY_USERNAME_MESSAGE = "Username cannot be empty.";

    public static final String WRONG_PASSWORD_LENGTH_MESSAGE = "Password name must be at least 4 symbols long.";
    public static final String NULL_PASSWORD_MESSAGE = "Password cannot be null.";
    public static final String EMPTY_PASSWORD_MESSAGE = "Password cannot be empty.";
    public static final String PASSWORDS_ARE_NOT_EQUALS = "Password not match.";

    public static final String WRONG_FIRST_NAME_LENGTH_MESSAGE = "First name must be at least 4 symbols long.";
    public static final String WRONG_FIRST_NAME_CAPITAL_CASE_MESSAGE = "First name must start with capital letter.";
    public static final String NULL_FIRST_NAME_MESSAGE = "First name cannot be null.";
    public static final String EMPTY_FIRST_NAME_MESSAGE = "First name cannot be empty.";

    public static final String WRONG_LAST_NAME_LENGTH_MESSAGE = "Last name must be at least 4 symbols long.";
    public static final String WRONG_LAST_NAME_CAPITAL_CASE_MESSAGE = "Last name must start with capital letter.";
    public static final String NULL_LAST_NAME_MESSAGE = "Last name cannot be null.";
    public static final String EMPTY_LAST_NAME_MESSAGE = "Last name cannot be empty.";

}
