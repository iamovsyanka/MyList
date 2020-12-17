package by.ovsyanka.mylist.Repository;

import by.ovsyanka.mylist.Entity.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskRepositoryTests {

    @Autowired
    private  TaskRepository taskRepository;

    @Test
    public void getUserByName() {
//        List<Task> tasks = taskRepository.findAll();
//        System.out.println(tasks);
//
//        Assert.assertNotNull(tasks);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat("d.MM.YYYY", Locale.getDefault());
        String myString = dayFormat.format(calendar.getTime());
        System.out.println(myString);
    }

    @Test
    public void getTestById() {
        Assert.assertNotNull(taskRepository.findById(4L));
    }
}
