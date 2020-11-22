package by.ovsyanka.mylist.Service;

import by.ovsyanka.mylist.Dto.UserDto;
import by.ovsyanka.mylist.Entity.User;

import java.util.List;

public interface UserService {
    User register(UserDto userDto) throws Exception;
    List<User> getAllUsers();
    User findByName(String name);
    User findById(Long id);
    void deleteById(Long id);
}
