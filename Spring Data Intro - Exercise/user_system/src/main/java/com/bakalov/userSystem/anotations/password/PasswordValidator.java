package com.bakalov.userSystem.anotations.password;

import com.bakalov.userSystem.anotations.AnnotationUtil;
import com.bakalov.userSystem.utilities.Constants;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    public static final Pattern PATTERN_LOWER = Pattern.compile("[a-z]");
    public static final Pattern PATTERN_UPPER = Pattern.compile("[A-Z]");
    public static final Pattern PATTERN_DIGIT = Pattern.compile("[0-9]");
    public static final Pattern PATTERN_SYMBOL = Pattern.compile("[!@#$%^&*()_+<>?]");

    private int minLength;
    private int maxLength;
    private boolean hasUpper;
    private boolean hasLower;
    private boolean hasDigit;
    private boolean hasSpecialSymbol;


    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.hasUpper = password.containUpperCase();
        this.hasLower = password.containLowerCase();
        this.hasDigit = password.containsDigit();
        this.hasSpecialSymbol = password.containSpecialSymbols();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext context) {
        if (charSequence == null) {
            AnnotationUtil.setErrorMessage(context, Constants.PASSWORD_CANNOT_BE_NULL);
            return false;
        }

        if (charSequence.length() < this.minLength) {
            AnnotationUtil.setErrorMessage(context, Constants.PASSWORD_TOO_SHORT);
            return false;
        }

        if (charSequence.length() > this.maxLength) {
            AnnotationUtil.setErrorMessage(context, Constants.PASSWORD_TOO_LONG);
            return false;
        }

        String password = charSequence.toString();

        if (!PATTERN_LOWER.matcher(password).find() && this.hasLower) {
            AnnotationUtil.setErrorMessage(context, Constants.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER);
            return false;
        }

        if (!PATTERN_UPPER.matcher(password).find() && this.hasUpper) {
            AnnotationUtil.setErrorMessage(context, Constants.PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER);
            return false;
        }

        if (!PATTERN_DIGIT.matcher(password).find() && this.hasDigit) {
            AnnotationUtil.setErrorMessage(context, Constants.PASSWORD_SHOULD_CONTAIN_DIGIT);
            return false;
        }

        if (!PATTERN_SYMBOL.matcher(password).find() && this.hasSpecialSymbol) {
            AnnotationUtil.setErrorMessage(context, Constants.PASSWORD_SHOULD_CONTAIN_SPECIAL_SYMBOL);
            return false;
        }

        return true;
    }
}
