package com.example.smartcampus.smartcampus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI openApi(){
       return new OpenAPI().info(
               new Info()
                       .title("Smart Campus Helpdesk API")
                       .description("A complaint management system for students, " +
                               "staff and administrators. Supports authentication, " +
                               "complaint assignment, dashboards, " +
                               "activity history and complaint tracking.\n" )
                       .version("1.0")
                       .contact(
                               new Contact()
                                       .name("Puredla Sridevi")
                                       .email("srid15880@gmail.com")
                       )
       );
    }
}
