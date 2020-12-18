package by.ovsyanka.mylist.Rest;

import by.ovsyanka.mylist.Dto.AuthUserDto;
import by.ovsyanka.mylist.Dto.RegisterUserDto;
import by.ovsyanka.mylist.Entity.Role;
import by.ovsyanka.mylist.Entity.User;
import by.ovsyanka.mylist.Exception.UserNameNotFoundException;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Security.jwt.JwtTokenProvider;
import by.ovsyanka.mylist.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.BindException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/")
@AllArgsConstructor
public class AuthAndRegisterRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("login")
    @Loggable
    public ResponseEntity<Map<Object, Object>> login(@Valid @RequestBody AuthUserDto authUserDto) {
        try {
            String username = authUserDto.getName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authUserDto.getPassword()));
            User user = userService.findByName(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());
            List<String> roleNames = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            response.put("roles", roleNames);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException | UserNameNotFoundException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    @Loggable
    public ResponseEntity<User> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        try {
            userService.register(registerUserDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> handleBindException (BindException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
