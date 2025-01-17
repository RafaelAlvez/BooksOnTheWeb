package br.com.books.web.usuarios;

import br.com.books.web.usuarios.config.JPAConfig;
import br.com.books.web.usuarios.config.OpenApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({OpenApiConfig.class, JPAConfig.class})
@EnableConfigurationProperties
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
