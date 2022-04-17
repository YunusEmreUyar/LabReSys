package com.yemreu.main.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("report-images", registry);
    }

    private void exposeDirectory(String directionName, ResourceHandlerRegistry registry) {
        Path uploadDirection = Paths.get(directionName);
        String uploadPath = uploadDirection.toFile().getAbsolutePath();
        if (directionName.startsWith("../")) {
            directionName = directionName.replace("../", "");
        }
        registry
                .addResourceHandler("/" + directionName + "/**")
                .addResourceLocations("file:/"+ uploadPath + "/");
    }
}
