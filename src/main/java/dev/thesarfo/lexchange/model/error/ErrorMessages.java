package dev.thesarfo.lexchange.model.error;

public class ErrorMessages {

    private ErrorMessages(){}

    public static final String USER_NOT_FOUND = "User not found with username: ";
    public static final String VALID_EMAIL_ADDRESS = "Please enter valid e-mail address";
    public static final String MINIMUM_EMAIL_LENGTH = "Minimum e-mail length is 7 characters.";
    public static final String MINIMUM_USERNAME_LENGTH = "Minimum username length is 7 characters.";
    public static final String USER_ALREADY_EXISTS = "User already exists with this username or email";
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String MINIMUM_PASSWORD_LENGTH = "Minimum password length is 8 characters.";
    public static final String PROFILE_NOT_FOUND = "Profile not found ";
    public static final String OLD_PASSWORD_INCORRECT = "Old password is incorrect";
    public static final String NEW_PASSWORD_SAME_AS_OLD = "New password cannot be the same as old password";
    public static final String OLD_AND_NEW_PASSWORD_EQUAL = "New password cannot be the same as the old password";
    public static final String INVALID_FILE = "Only png and jpeg formats are allowed";
    public static final String FILE_ERROR = "Could not upload this file";
}
