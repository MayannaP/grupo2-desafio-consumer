package desafio.grupo2.services;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class Consumer {
	
	public static void readMessage(String groupId) throws InterruptedException, ExecutionException, IOException{
		var consumer = new KafkaConsumer<String,String>(properties(groupId));
        consumer.subscribe(Collections.singletonList("UM_TOPICO"));
        
        while (true) {
            var products = consumer.poll(Duration.ofMillis(300));
            for (ConsumerRecord<String, String> product: products) {
            	File service = new File();
            	
            	service.saveProductsToDB(product.value());
            }
        }
	}
	
	private static Properties properties(String groupId) { 
		var properties = new Properties(); 
		
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); 
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
	    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
	    properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString()); // para enviar dados em consumidores diferentes
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
	    
		return properties; 
	}
}
