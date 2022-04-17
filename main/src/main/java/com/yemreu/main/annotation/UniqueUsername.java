package com.yemreu.main.annotation;

import com.yemreu.main.validator.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "Username address is already registered";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
