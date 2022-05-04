package desafio.grupo2;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import desafio.grupo2.services.Consumer;


@SpringBootApplication
public class Grupo2Application {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		SpringApplication.run(Grupo2Application.class, args);
		Consumer.readMessage("test-consumer-group");
	}

}
