package by.ovsyanka.mylist.Rest;

import by.ovsyanka.mylist.Entity.Task;
import by.ovsyanka.mylist.Entity.User;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/api/tasks/")
public class EmailController {
    public final JavaMailSender emailSender;
    private final UserService userService;

    public EmailController(UserService userService, JavaMailSender emailSender) {
        this.userService = userService;
        this.emailSender = emailSender;
    }

    @GetMapping(value = "sendNotify")
    @Loggable
    public ResponseEntity<Object> sendSimpleEmail(Principal principal) {

        SimpleMailMessage message = new SimpleMailMessage();
        User user = userService.findByName(principal.getName());
        message.setTo(user.getEmail());
        message.setSubject("MyList");
        StringBuilder msg = new StringBuilder("Hello, I want to remind you that there are " + user.getTasks().size() +" more tasks on your list\n\n");

        for (Task task:
                user.getTasks()) {
            if(task.getDateOfDeadline() != null) {
                msg.append("✓ Task: ").append(task.getName()).append(", deadline: ").append(task.getDateOfDeadline()).append("\n");
            }
        }

        message.setText(String.valueOf(msg));
        this.emailSender.send(message);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
