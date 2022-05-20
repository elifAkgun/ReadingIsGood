package code.elif.readingIsGood.customer.service.validation.anotation;

import code.elif.readingIsGood.customer.service.validation.UniqueUserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUserNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmailAddress {
	
	String message() default "{readingIsGood.constraints.username.UniqueUserName.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
