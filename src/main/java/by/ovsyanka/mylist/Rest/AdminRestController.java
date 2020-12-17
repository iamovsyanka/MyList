package by.ovsyanka.mylist.Rest;

import by.ovsyanka.mylist.Dto.RegisterUserDto;
import by.ovsyanka.mylist.Dto.UserDto;
import by.ovsyanka.mylist.Entity.User;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/admin/")
@AllArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @Loggable
    @GetMapping(value = "{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable(name = "name") String name) {
        User user = userService.findByName(name);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Loggable
    @GetMapping(value = "list")
    public ResponseEntity<Page<UserDto>> getUsers(Pageable pageable) {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(UserDto.fromUser(user));
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userDtos.size());
        Page<UserDto> result
                = new PageImpl<>(userDtos.subList(start, end), pageable, userDtos.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
