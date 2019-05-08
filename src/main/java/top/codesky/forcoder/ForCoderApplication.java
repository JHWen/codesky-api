package top.codesky.forcoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"top.codesky.forcoder.dao"})
public class ForCoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForCoderApplication.class, args);
    }

}
