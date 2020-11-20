package by.ovsyanka.mylist.Dto;

import by.ovsyanka.mylist.Entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserDto {

    @Null //автогенерация в БД
    private Long userId;

    @NotNull
    @NotBlank(message = "Please, enter the userName!")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 symbols")
    @Pattern(regexp = "^[A-z\\d*]{4,20}$")
    private String userName;

    @NotNull
    @NotBlank(message = "Please, enter the password!")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 symbols")
    private String password;

    @NotBlank(message = "Please, enter the repeat password!")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 symbols")
    private String repeatPassword;

    @Email(message = "Wrong email address")
    private String email;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());

        return userDto;
    }
}
