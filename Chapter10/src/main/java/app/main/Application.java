package app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.course")
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        //固定写法入口类
        SpringApplication.run(Application.class,args);
    }
}

