package emssanar.salud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;


//@ComponentScan ("emssanar.salud.persistence.crud")
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
public class EmssanarSaludApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmssanarSaludApplication.class, args);
	}

}
