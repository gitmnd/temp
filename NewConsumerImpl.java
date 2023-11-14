package com.mycompany.app2;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class NewConsumerImpl {


    public static void main(String[] args) {
        new NewConsumerImpl().consumeIt();
    }

    public void consumeIt(){
        String bootstrapServers = "localhost:9092";
        String groupId = "general-group";
        String topic = "testtopic";

        // create consumer configs
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        // subscribe consumer to our topic(s)
        consumer.subscribe(Arrays.asList(topic), new HandleRebalance());

        // poll for new data
        while(true){
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));
            System.out.println(records.count());
            for (ConsumerRecord<String, String> record : records){
                System.out.println("Key: " + record.key() + ", Value: " + record.value() + ", Partition: " + record.partition() + ", Offset:" + record.offset());
            }
            consumer.commitAsync();
        }
    }
}
