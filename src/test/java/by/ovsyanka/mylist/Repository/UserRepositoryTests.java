package by.ovsyanka.mylist.Repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUserByName() {
        Assert.assertNotNull(userRepository.findByName("Anna"));
    }

    @Test
    public void getNullUserByName() {
        Assert.assertNull(userRepository.findByName("Admin"));
    }
}
