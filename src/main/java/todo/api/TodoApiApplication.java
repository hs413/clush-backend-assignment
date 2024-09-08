package todo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TodoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApiApplication.class, args);
    }

}
