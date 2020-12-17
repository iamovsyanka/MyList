package by.ovsyanka.mylist.Dto;

import by.ovsyanka.mylist.Entity.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
