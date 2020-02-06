package com.kafka.sb.kafkasb.controller;

/**
 * Created by chetan on 23/1/20.
 */

import com.kafka.sb.kafkasb.model.User;
import com.kafka.sb.kafkasb.producer.Producer;
import com.kafka.sb.kafkasb.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    Producer producer1;
    @RequestMapping("/")
    public String index() {
        //producer1.sendMessage(String.valueOf(System.currentTimeMillis()));
        producer1.sendMessageWithCallback(String.valueOf(System.currentTimeMillis()));
        for (int i = 0; i < 1; i++) {
            User user = CommonUtil.generateCustomerUser();
            producer1.send(user);
            LOGGER.info("Message produced--->\n");
        }
        return "Greetings from Spring Boot!";
    }

}
