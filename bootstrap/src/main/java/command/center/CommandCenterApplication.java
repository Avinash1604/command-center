package command.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CommandCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommandCenterApplication.class, args);
	}

}
