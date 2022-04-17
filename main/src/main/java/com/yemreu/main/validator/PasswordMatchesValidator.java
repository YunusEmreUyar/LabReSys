package com.yemreu.main.validator;

import com.yemreu.main.annotation.PasswordMatches;
import com.yemreu.main.dto.UserRegisterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches passwordMatches) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserRegisterDto userRegisterDto = (UserRegisterDto) o;
        return userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword());
    }
}
