package Todo_Test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication
@MapperScan("Todo_Test.mapper")
public class TestTodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestTodoApplication.class, args);
    }
}
