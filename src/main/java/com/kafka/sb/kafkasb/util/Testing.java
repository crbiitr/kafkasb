package com.kafka.sb.kafkasb.util;

import com.kafka.sb.kafkasb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by chetan on 26/1/20.
 */
public class Testing {
    private static final Logger LOGGER = LoggerFactory.getLogger(Testing.class);

    public static void main(String[] args) {
//        for (int i = 0; i < 9999999; i++) {
            for (int i = 0; i < 10; i++) {
            int id = getRandomNumberInRange(1, 9999999);
            int age = getRandomNumberInRange(10, 100);
            User user= new User(givenUsingJava8_whenGeneratingRandomAlphanumericString(),givenUsingJava8_whenGeneratingRandomAlphabeticString(),age,getRandomDoubleBetweenRange(10,20987),givenUsingJava8_whenGeneratingRandomAlphabeticString(),System.currentTimeMillis());
            System.out.println(user.toString());


        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static double getRandomDoubleBetweenRange(double min, double max){
        double x = (Math.random()*((max-min)+1))+min;
        return x;
    }
    public static String givenUsingJava8_whenGeneratingRandomAlphabeticString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String givenUsingJava8_whenGeneratingRandomAlphanumericString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
