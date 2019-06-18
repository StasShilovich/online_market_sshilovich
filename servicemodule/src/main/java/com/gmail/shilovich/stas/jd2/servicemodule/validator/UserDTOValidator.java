package com.gmail.shilovich.stas.jd2.servicemodule.validator;


import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "userDTO.surname.empty.or.whitespace");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "userDTO.name.empty.or.whitespace");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "userDTO.email.empty.or.whitespace");
        UserDTO userDTO = (UserDTO) obj;
        Pattern patternEmail = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
        Pattern patternSurname = Pattern.compile("^[a-zA-Z_]{3,40}$");
        Pattern patternName = Pattern.compile("^[a-zA-Z_]{3,40}$");
        Pattern patternPatronymic = Pattern.compile("^[a-zA-Z_]{3,40}$");

        if (!(patternName.matcher(userDTO.getName()).matches())) {
            errors.rejectValue("name", "userDTO.name.invalid");
        }
        if (!(patternSurname.matcher(userDTO.getSurname()).matches())) {
            errors.rejectValue("surname", "userDTO.surname.invalid");
        }
        if (!(patternEmail.matcher(userDTO.getEmail()).matches())) {
            errors.rejectValue("email", "userDTO.email.invalid");
        }
        if (!(patternPatronymic.matcher(userDTO.getPatronymic()).matches())) {
            errors.rejectValue("middleName", "userDTO.middleName.invalid");
        }
    }
}
