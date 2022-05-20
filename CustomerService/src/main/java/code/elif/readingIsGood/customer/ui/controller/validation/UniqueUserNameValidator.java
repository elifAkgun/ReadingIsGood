package code.elif.readingIsGood.customer.ui.controller.validation;

import code.elif.readingIsGood.customer.service.UserService;
import code.elif.readingIsGood.customer.service.dto.UserDTO;
import code.elif.readingIsGood.customer.ui.controller.validation.anotation.UniqueEmailAddress;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//First parameter is for annotation
//Second parameter is for field which will be validated (emailAddress)
public class UniqueUserNameValidator implements ConstraintValidator<UniqueEmailAddress, String> {

	@Autowired
	UserService userService;
	
	@Override
	public boolean isValid(String emailAddress, ConstraintValidatorContext context) {
		UserDTO inDbUser = null;

		if(!emailAddress.isEmpty()) {
			inDbUser = userService.getUserDetailsByEmail(emailAddress);
		}
		if(inDbUser==null) {
			return true;
		}
		return false;
	}

}
