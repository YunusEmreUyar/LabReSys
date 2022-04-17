package com.yemreu.main.annotation;

import com.yemreu.main.validator.UniqueTcValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueTcValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTc {
    String message() default "TC must be unique and yours!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

