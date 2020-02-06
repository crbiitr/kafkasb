package com.kafka.sb.kafkasb.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by chetan on 26/1/20.
 */
@Service
public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @Value(value = "${kafka.default.group.id}")
    private String defaultGroupId;


    @KafkaListener(topics = "${kafka.default.topic}", groupId = "${kafka.default.group.id}",containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        LOGGER.info("Received Message listen consumer: " + message);
    }


    @KafkaListener(topics = "${kafka.user.json.topic}", groupId = "${kafka.default.group.id}",containerFactory = "userKafkaListenerContainerFactory")
    public void userListen(ConsumerRecord record) {
        LOGGER.info("Consuming topic : [ " + record.topic() +", Partition: "+ record.partition() +", Offser: "+record.offset() +", Timestamp "+record.timestamp() +" ]\n[ Key:  "+record.key() +" => record: "+ record.value() + " ]");
        //LOGGER.info("Received Message in userListen consumer: " + record.value().toString());
    }

}
