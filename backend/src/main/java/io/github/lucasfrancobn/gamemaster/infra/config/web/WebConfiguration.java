package io.github.lucasfrancobn.gamemaster.infra.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path path = Paths.get(uploadDir).normalize().toAbsolutePath();

        String resourceLocation = path.toUri().toString();

        if (!resourceLocation.endsWith("/")) {
            resourceLocation += "/";
        }

        registry
                .addResourceHandler("/gamemaster/api/v1/uploads/**")
                .addResourceLocations(resourceLocation);
    }
}
