package by.ovsyanka.mylist.Service;

import by.ovsyanka.mylist.Dto.UserDto;
import by.ovsyanka.mylist.Entity.User;

import java.util.List;

public interface UserService {
    User registration(User user) throws Exception;
    List<User> getAll();
    User findByUserName(String username);
    User findById(Long id);
    void delete(Long id);
}
