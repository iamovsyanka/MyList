package by.ovsyanka.mylist.Service.impl;

import by.ovsyanka.mylist.Dto.UserDto;
import by.ovsyanka.mylist.Entity.Role;
import by.ovsyanka.mylist.Entity.User;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Repository.RoleRepository;
import by.ovsyanka.mylist.Repository.UserRepository;
import by.ovsyanka.mylist.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Loggable
    public User registration(User user) {
        Role roleUser = roleRepository.findByRole("user");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        return userRepository.save(user);
    }

    @Override
    @Loggable
    public List<User> getAll() {
        List<User> result = userRepository.findAll();

        return result;
    }

    @Override
    @Loggable
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    @Loggable
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        return result;
    }

    @Override
    @Loggable
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
