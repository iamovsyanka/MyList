package by.ovsyanka.mylist.Repository;

import by.ovsyanka.mylist.Entity.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    }

    @Test
    public void getTestById() {
        Assert.assertNotNull(taskRepository.findById(4L));
    }
}
