package com.thanhdao.course_online.config.mvc;

import com.thanhdao.course_online.anotations.V1RestController;
import com.thanhdao.course_online.anotations.V2RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class RestControllerAnnotationConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api/v1", c -> c.isAnnotationPresent(V1RestController.class));
        configurer.addPathPrefix("/api/v2", c -> c.isAnnotationPresent(V2RestController.class));
        
    }
}
