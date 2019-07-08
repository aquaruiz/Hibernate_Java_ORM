package com.bakalov.userSystem.anotations;

import javax.validation.ConstraintValidatorContext;

public final class AnnotationUtil {

    private AnnotationUtil() {
    }

    public static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}
