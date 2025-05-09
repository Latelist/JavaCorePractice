package salad_leaf.spring_data_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringDataPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataPracticeApplication.class, args);
	}

}
