package by.ovsyanka.teamwork.services.contracts;

import by.ovsyanka.teamwork.dto.UserDto;
import by.ovsyanka.teamwork.entity.User;

public interface UserService {
    User login(UserDto userDto);
    void register(UserDto userDto) throws Exception;
}