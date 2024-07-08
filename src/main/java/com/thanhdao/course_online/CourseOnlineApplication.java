package com.thanhdao.course_online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CourseOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseOnlineApplication.class, args);
    }
    
}
