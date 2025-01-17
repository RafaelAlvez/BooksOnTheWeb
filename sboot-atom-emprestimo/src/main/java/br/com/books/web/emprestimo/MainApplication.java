package br.com.books.web.emprestimo;

import br.com.books.web.emprestimo.config.JPAConfig;
import br.com.books.web.emprestimo.config.OpenApiConfig;
import br.com.books.web.emprestimo.config.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({OpenApiConfig.class, JPAConfig.class, RabbitConfig.class})
@EnableConfigurationProperties
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
