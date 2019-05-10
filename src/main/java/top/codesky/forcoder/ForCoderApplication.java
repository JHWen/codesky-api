package top.codesky.forcoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.codesky.forcoder.service.StorageService;

@SpringBootApplication
@MapperScan(basePackages = {"top.codesky.forcoder.dao"})
public class ForCoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForCoderApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> storageService.init();
    }
}
