package com.devteria.identity_service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Target({ ElementType.FIELD}) // Target: Annotation được apply ở đâu
@Retention(RetentionPolicy.RUNTIME) //Rêtntion: Annotation được sử lý lúc nào
@Constraint(
        validatedBy = {DobValidator.class}
)
public @interface DobConstraint {
    String message() default "{Invalid date of birth}";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
