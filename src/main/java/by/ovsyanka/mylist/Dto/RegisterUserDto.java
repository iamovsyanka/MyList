package by.ovsyanka.mylist.Dto;

import by.ovsyanka.mylist.Entity.User;
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class RegisterUserDto {

    @Null
    private Long userId;

    @NotNull
    @NotBlank(message = "Please, enter the userName!")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 symbols")
    @Pattern(regexp = "^[A-z\\d*]{4,20}$")
    private String name;

    @NotNull
    @NotBlank(message = "Please, enter the password!")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 symbols")
    private String password;

    @NotNull
    @NotBlank(message = "Please, enter the repeat password!")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 symbols")
    private String repeatPassword;

    @Email(message = "Wrong email address")
    private String email;

    public static RegisterUserDto fromUser(User user) {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUserId(user.getId());
        registerUserDto.setName(user.getName());

        return registerUserDto;
    }
}
