package com.kafka.sb.kafkasb.producer;

import com.kafka.sb.kafkasb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by chetan on 24/1/20.
 */
@Service
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;
    @Value(value = "${kafka.default.topic}")
    private String topicName;

    @Value(value = "${kafka.user.json.topic}")
    private String userTopic;

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }

    /**
     * Kafka is a fast stream processing platform. So it's a better idea to handle the results asynchronously so that the subsequent messages do not wait for the result of the previous message. We can do this through a callback:
     * @param message
     */
    public void sendMessageWithCallback(String message) {

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]"
                        + ", Partition: " + result.getRecordMetadata().partition() );
            }
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.info("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
    public void send(User payload) {
        Message<User> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, userTopic)
                .build();
        userKafkaTemplate.send(message);
        ListenableFuture<SendResult<String, User>> future =
                userKafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {

            @Override
            public void onSuccess(SendResult<String, User> result) {
                LOGGER.info("Sent message=[" + message.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]"
                        + ", Partition: " + result.getRecordMetadata().partition() );
            }
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.info("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });


    }
}
