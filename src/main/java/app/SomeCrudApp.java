package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"app","app.data"})
@SpringBootApplication
public class SomeCrudApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SomeCrudApp.class, args);
        System.out.println("Component mySql : " + context.containsBeanDefinition("mySql"));
        System.out.println("Component mySqlSomeObjectCrud : " + context.containsBeanDefinition("mySqlSomeObjectCrud"));

    }
}
