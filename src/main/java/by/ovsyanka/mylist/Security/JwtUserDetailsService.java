package by.ovsyanka.mylist.Security;

import by.ovsyanka.mylist.Entity.User;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Security.jwt.JwtUser;
import by.ovsyanka.mylist.Security.jwt.JwtUserFactory;
import by.ovsyanka.mylist.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Loggable
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }
}
