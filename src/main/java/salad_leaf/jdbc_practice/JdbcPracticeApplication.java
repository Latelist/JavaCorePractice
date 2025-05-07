package salad_leaf.jdbc_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class JdbcPracticeApplication {

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
		SpringApplication.run(JdbcPracticeApplication.class, args);
	}

}
