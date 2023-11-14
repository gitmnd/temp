package com.mycompany.app2;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.internals.Topic;

import java.util.Collection;

public class HandleRebalance implements ConsumerRebalanceListener {
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {
        for(TopicPartition partition: collection){
            System.out.println("\n");
            System.out.println("partition "+ partition.partition() + " revoked from me");
        }
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {
        for(TopicPartition partition: collection){
            System.out.println("partition "+ partition.partition() + "  assigned to me");
        }
    }
}
