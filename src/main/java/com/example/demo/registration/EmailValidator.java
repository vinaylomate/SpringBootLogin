package com.example.demo.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String email) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }
}
