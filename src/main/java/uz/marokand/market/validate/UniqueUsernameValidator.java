package uz.marokand.market.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.marokand.market.service.UserService;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UserValidity,String> {
    private final UserService userService;
    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isUserAlreadyPresent(s)&&!userService.existByEmail(s);
    }
}
