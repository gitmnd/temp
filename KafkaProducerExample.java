package com.mycompany.app;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerExample {

	 public static void main(String[] args) {
        
        // Kafka producer configuration
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	
    	// Create Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        String topicName = "testtopic";

        // Send messages to Kafka topic
        for (int i = 1; i <= 100; i++) {
        	if(i%2==0) {
        		String message = "Even- " + i;
                ProducerRecord<String, String> record = new ProducerRecord<>(topicName, "EVENHIUMSUPER", message);
                Future<RecordMetadata> future = producer.send(record);
                try {
                    // Wait for the send operation to complete and get the RecordMetadata
                    RecordMetadata metadata = future.get();
                    System.out.println("EVEN Message sent to partition " + metadata.partition() +
                                       " with offset " + metadata.offset());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } 
        	}else {
        		String message = "Odd- " + i;
                ProducerRecord<String, String> record = new ProducerRecord<>(topicName, "ODDPOPQUESQSRED", message);
                Future<RecordMetadata> future = producer.send(record);
                try {
                    // Wait for the send operation to complete and get the RecordMetadata
                    RecordMetadata metadata = future.get();
                    System.out.println("ODD Message sent to partition " + metadata.partition() +
                                       " with offset " + metadata.offset());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } 
        	}
            
        }

        // Close the producer
        producer.close();
    }
}
