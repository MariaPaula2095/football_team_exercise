package org.example.football_team_management.cofiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class swaggerConfiguration {
        @Bean
        public OpenAPI customOpenAPI(){
            return new OpenAPI()
                    .info(new Info()
                            .title ("API FOOTBALL_TEAM")
                            .version ("1.0")
                            .description ("API PARA THE FOOTBALL TEAM")
                            .contact(new Contact()
                                    .name ("DESARROLLADOR DE API")
                                    .email("prueba@prueba.com")));

        }

    }


