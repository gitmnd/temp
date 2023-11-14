package com.mycompany.app2;



import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerSample {
    public static void main(String[] args) {
    	
    	 // Set up consumer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092"); // Kafka broker(s) address
        props.put("group.id", "test-group"); // Consumer group ID
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        // Create Kafka consumer
        Consumer<String, String> consumer = new KafkaConsumer<>(props);

        String topicName = "testtopic";

        // Subscribe to topics
        consumer.subscribe(Collections.singletonList(topicName));

        // Poll for messages
        try {
            System.out.println("Started consuming..");
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                // Process the fetched records
                records.forEach(record -> {
                    System.out.println("Received message: " + record.value());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}

