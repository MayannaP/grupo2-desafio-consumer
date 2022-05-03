package desafio.grupo2.services;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

public class Consumer {
	public static void sendMessage(String key, String value) throws InterruptedException, ExecutionException{
		var consumer = new KafkaConsumer<String,String>(properties());
        consumer.subscribe(Collections.singletonList("KAFKA_TOPIC"));
	}
	
	private static Properties properties() { 
		var properties = new Properties(); 
		
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092"); 
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	    
		
		return properties; 
	}
}
