package by.ovsyanka.mylist.Rest;

import by.ovsyanka.mylist.Dto.RegisterUserDto;
import by.ovsyanka.mylist.Entity.User;
import by.ovsyanka.mylist.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/admin/")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<RegisterUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        RegisterUserDto result = RegisterUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
