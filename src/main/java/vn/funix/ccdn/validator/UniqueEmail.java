package vn.funix.ccdn.validator;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/*
 * @Constraint(…) —indicates what class is implementing the constraint for validation
 */
@Constraint(validatedBy = UniqueEmailValidator.class)


/*
 * @Retention(…) — in short, it indicates how long annotation will be making impact on our code
 *  (before or after compilation), 
 *  in above case — RetentionPolicy.RUNTIME 
 *  — it means that this annotation will be available after the runtime
 *
 */
@Retention(RetentionPolicy.RUNTIME)

/*
 * @Target(…) — indicates where this annotation can be applied, i.e. on a class, field, method.
 */
@Target({ ElementType.FIELD })


public @interface UniqueEmail {

	public String message() default "Email đã tồn tại!";

	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};
}
