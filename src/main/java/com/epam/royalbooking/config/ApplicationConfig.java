package com.epam.royalbooking.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@SpringBootApplication
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.epam.royalbooking**"})
@EntityScan({"com.epam.royalbooking.entities"})
@EnableJpaRepositories({"com.epam.royalbooking.dao"})
public class ApplicationConfig {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html;charset=utf-8");
        return viewResolver;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }
}