package by.ovsyanka.teamwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Enter the userName, please")
    @Pattern(regexp = "[A-z\\d*]{4,15}", message = "Username must be between 5 and 15 symbols and contain only Latin letters and numbers")
    private String userName;

    @Size(min = 8, max = 20,message = "Password must be between 8 and 20 symbols")
    @NotBlank(message = "Enter the password")
    private String password;

    @Size(min = 8, max = 20,message = "Password must be between 8 and 20 symbols")
    private String repeatPassword;
}
