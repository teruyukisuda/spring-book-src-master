package com.example.myapp.service;

import java.util.Random;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
    public String name() {
        int i = new Random().nextInt();
        return String.valueOf(i);
    }
    public String old() {
        String str = UUID.randomUUID().toString();
        return str;
    }
}
