package desafio.grupo2;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import desafio.grupo2.services.Consumer;


@SpringBootApplication
@EnableJpaRepositories

public class Grupo2Application {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Grupo2Application.class, args);
        Consumer consumer = (Consumer) context.getBean(Consumer.class);
		consumer.readMessage("test-consumer-group");
	}

}
