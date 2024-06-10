package at.htlleonding.Kinalyze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import at.htlleonding.Kinalyze.Database.DatabaseConnection;
import at.htlleonding.Kinalyze.Database.DatabaseOperations;

@SpringBootApplication
public class KinalyzeApplication {

	public static void main(String[] args) {
		System.out.println(DatabaseOperations.getKCode(1).getC_fileending());

		SpringApplication.run(KinalyzeApplication.class, args);
	}

}