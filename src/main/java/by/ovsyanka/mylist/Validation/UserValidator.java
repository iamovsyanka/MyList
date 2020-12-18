package by.ovsyanka.mylist.Validation;

import by.ovsyanka.mylist.Dto.RegisterUserDto;
import by.ovsyanka.mylist.Service.UserService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUserDto.class.equals(aClass);
    }

    @SneakyThrows
    @Override
    public void validate(Object o, Errors errors) {
        RegisterUserDto user = (RegisterUserDto) o;

        if(userService.findByName(user.getName()) != null) {
            errors.rejectValue("name", "", "This name is already exists");
        }
    }
}
