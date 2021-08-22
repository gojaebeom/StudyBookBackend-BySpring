package me.studybook.api;

import me.studybook.api.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class StudybookBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudybookBackendApplication.class, args);
    }

}
