package io.github.dev_alan87.sales.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import io.github.dev_alan87.sales.validation.constraintValidator.NotEmptyListValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {
    
    String message() default "List cannot be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}