package com.github.danyeu.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Service
public class NumberService {

    private Integer currentNumber = 0;

    public String weclome() {
        String s;
        s = "Welcome! The current number is " + currentNumber + " \n" +
                "Visit /even, /prime, /add/1, or /seven to see facts about the current number. \n" +
                "To get a new number (from 8081/random), visit /update.\n";

        return s;
    }

    public String even() {
        if (currentNumber % 2 == 0) {
            return String.format("The number %d is even.", currentNumber);
        }
        return String.format("The number %d is not even.", currentNumber);
    }

    public String prime() {
        // number is >= 0
        if (currentNumber <= 1) {
            return String.format("The number %d is not a prime number.", currentNumber);
        }
        if (currentNumber == 2) {
            return "The number 2 is a prime number.";
        }
        for (int i = 2; i <= currentNumber / 2 + 1; i++) {
            if (currentNumber % i == 0) {
                return String.format("The number %d is not a prime number.", currentNumber);
            }
        }
        return String.format("The number %d is a prime number.", currentNumber);
    }

    public String add(Long n) {
        return String.format("%d + %d = %d", currentNumber, n, currentNumber + n);
    }

    public String seven() {
        String s = String.valueOf(currentNumber);
        int sum = 0;
        boolean containsSeven = false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '7') {
                containsSeven = true;
                break;
            }
        }

        if (containsSeven) {
            return String.format("The number %s contains a 7!", s);
        } else {
            return String.format("The number %s doesn't contain a 7!", s);
        }

    }

    public boolean updateNumber() {
        try {
            this.currentNumber = requestNumber().get("number");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Map<String, Integer> requestNumber() throws IOException {
        URL url = new URL("http://random:8081/random");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> map = mapper.readValue(url, Map.class);
        return map;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }
}