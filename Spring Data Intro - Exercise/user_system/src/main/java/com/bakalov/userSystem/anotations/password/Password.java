package com.bakalov.userSystem.anotations.password;


import com.bakalov.userSystem.utilities.Constants;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default Constants.INVALID_PASSWORD;

    int minLength() default 6;

    int maxLength() default 30;

    boolean containsDigit() default false;

    boolean containLowerCase() default false;

    boolean containUpperCase() default false;

    boolean containSpecialSymbols() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
