package by.ovsyanka.teamwork.services;

import by.ovsyanka.teamwork.config.Loggable;
import by.ovsyanka.teamwork.config.Mapper;
import by.ovsyanka.teamwork.dto.UserDto;
import by.ovsyanka.teamwork.entity.User;
import by.ovsyanka.teamwork.repositories.RoleRepository;
import by.ovsyanka.teamwork.repositories.UserRepository;
import by.ovsyanka.teamwork.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Loggable
    public User login(UserDto userDto){
        User user = Mapper.map(userDto, User.class);

        user.setUserName(user.getUserName().toLowerCase());
        User possibleUser = userRepository
                .findUserByUsername(userDto.getUserName()).get();
        if(!userDto.getPassword().equals(possibleUser.getPassword())) return  null;

        return possibleUser;
    }

    @Override
    @Loggable
    public void register(UserDto userDto) throws Exception {
        if(userRepository
                .findUserByUsername(userDto.getUserName())
                .isPresent()) throw new Exception("User has already registered");

        if(!userDto
                .getPassword()
                .equals(userDto
                        .getRepeatPassword())){
            throw new Exception("Passwords are not equal");
        }

        User user = new User();
        user.setUserName(userDto.getUserName());

        //user.setRole(roleRepository.findRoleByName("USER").get());
        userRepository.save(user);
    }
}