package by.ovsyanka.mylist.Rest;

import by.ovsyanka.mylist.Dto.UserDto;
import by.ovsyanka.mylist.Entity.User;
import by.ovsyanka.mylist.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/api/v1/")
public class RegistrationRestController {

    private final UserService userService;

    @Autowired
    public  RegistrationRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("register")
    public ModelAndView showRegistrationPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("registration");
        UserDto form = new UserDto();
        model.addAttribute("form", form);

        return modelAndView;
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody UserDto user) {
        try {
            userService.register(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
