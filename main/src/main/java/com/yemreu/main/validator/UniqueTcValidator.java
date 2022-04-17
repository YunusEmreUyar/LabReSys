package com.yemreu.main.validator;

import com.yemreu.main.annotation.UniqueTc;
import com.yemreu.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTcValidator implements ConstraintValidator<UniqueTc, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String tc, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByTc(tc);
    }
}
