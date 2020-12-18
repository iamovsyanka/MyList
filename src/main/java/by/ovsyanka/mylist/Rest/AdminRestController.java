package by.ovsyanka.mylist.Rest;

import by.ovsyanka.mylist.Dto.RegisterUserDto;
import by.ovsyanka.mylist.Dto.TaskDto;
import by.ovsyanka.mylist.Dto.UserDto;
import by.ovsyanka.mylist.Entity.User;
import by.ovsyanka.mylist.Exception.UserNameNotFoundException;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Get user by name", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return user",
                    content = {@Content(mediaType = "application/json")})
    })
    @Loggable
    @GetMapping(value = "{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable(name = "name") String name) throws UserNameNotFoundException {
        User user = userService.findByName(name);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Get all users", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return users",
                    content = {@Content(mediaType = "application/json")})
    })
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

    @Operation(summary = "Delete user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete user",
                    content = {@Content(mediaType = "application/json")})
    })
    @Loggable
    @DeleteMapping(value = "{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
