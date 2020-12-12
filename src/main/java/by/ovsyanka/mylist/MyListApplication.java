package by.ovsyanka.mylist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =  @Info(title = "MyList API"))
public class MyListApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyListApplication.class, args);
    }
}
