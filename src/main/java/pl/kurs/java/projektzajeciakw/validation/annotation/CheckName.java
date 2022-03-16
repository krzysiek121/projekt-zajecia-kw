package pl.kurs.java.projektzajeciakw.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckNameValidator.class)
public @interface CheckName {

    String message() default "INVALID_NAME";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

