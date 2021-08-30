package com.example.restaurantitaly.services.validation;


import com.example.restaurantitaly.common.Constants;
import com.example.restaurantitaly.domain.models.binding.UserRegisterModel;
import com.example.restaurantitaly.repositories.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Configuration
public class UserRegisterValidator implements Validator {

    private final UserRepository userRepository;

    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterModel.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        UserRegisterModel userRegisterModel = (UserRegisterModel) obj;

        if (userRegisterModel.getUsername() == null ||
                userRegisterModel.getUsername().isEmpty()) {
            errors.rejectValue(
                    "username",
                    Constants.EMPTY_USERNAME_MESSAGE,
                    Constants.EMPTY_USERNAME_MESSAGE);
        }
        if (userRegisterModel.getPassword() == null ||
                userRegisterModel.getPassword().isEmpty()) {
            errors.rejectValue(
                    "password",
                    Constants.EMPTY_PASSWORD_MESSAGE,
                    Constants.EMPTY_PASSWORD_MESSAGE);
        }

        if (userRegisterModel.getPassword().length() < 4 ) {
            errors.rejectValue(
                    "password",
                    Constants.WRONG_PASSWORD_LENGTH_MESSAGE,
                    Constants.WRONG_PASSWORD_LENGTH_MESSAGE

            );
        }
        if (userRegisterModel.getEmail() == null ||
                userRegisterModel.getEmail().isEmpty()) {
            errors.rejectValue(
                    "email",
                    Constants.EMPTY_EMAIL_MESSAGE,
                    Constants.EMPTY_EMAIL_MESSAGE);
        }
        if (errors.hasErrors()){
            return;
        }


        if (this.userRepository.findByUsername(userRegisterModel.getUsername()).isPresent()) {
            errors.rejectValue(
                    "username",
                    String.format(Constants.DUPLICATE_USERNAME, userRegisterModel.getUsername()),
                    String.format(Constants.DUPLICATE_USERNAME, userRegisterModel.getUsername()));
        }

        if (userRegisterModel.getUsername().length() < 5 ||
                userRegisterModel.getUsername().length() > 20) {
            errors.rejectValue(
                    "username",
                    Constants.WRONG_USERNAME_LENGTH_MESSAGE,
                    Constants.WRONG_USERNAME_LENGTH_MESSAGE
            );
        }

        if (userRegisterModel.getFirstName().length() < 4 ||
                userRegisterModel.getFirstName().length() > 20) {
            errors.rejectValue(
                    "firstName",
                    Constants.WRONG_FIRST_NAME_LENGTH_MESSAGE,
                    Constants.WRONG_FIRST_NAME_LENGTH_MESSAGE
            );
        }

        if (userRegisterModel.getLastName().length() < 4 ||
                userRegisterModel.getLastName().length() > 20) {
            errors.rejectValue(
                    "lastName",
                    Constants.WRONG_LAST_NAME_LENGTH_MESSAGE,
                    Constants.WRONG_LAST_NAME_LENGTH_MESSAGE
            );
        }

        if (!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            errors.rejectValue(
                    "password",
                    Constants.PASSWORDS_ARE_NOT_EQUALS,
                    Constants.PASSWORDS_ARE_NOT_EQUALS);
        }

        if (this.userRepository.findByEmail(userRegisterModel.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email",
                    String.format(Constants.EMPTY_ALREADY_EXIST, userRegisterModel.getEmail()),
                    String.format(Constants.EMPTY_ALREADY_EXIST, userRegisterModel.getEmail()));
        }
    }
}
