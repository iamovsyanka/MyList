package by.ovsyanka.mylist.Service;

import by.ovsyanka.mylist.Dto.RegisterUserDto;
import by.ovsyanka.mylist.Entity.User;
import by.ovsyanka.mylist.Exception.UserNameNotFoundException;

import java.util.List;

public interface UserService {
    User register(RegisterUserDto userDto) throws Exception;
    List<User> getAllUsers();
    User findByName(String name) throws UserNameNotFoundException;
    User findById(Long id);
    void deleteById(Long id);
}
