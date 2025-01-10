package com.example.member;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String password = "password";
        String password = "password123";
        String encodedPassword = encoder.encode(password);
        System.out.println("Encoded password: " + encodedPassword);

        // 検証
        boolean matches = encoder.matches(password, encodedPassword);
        System.out.println("Password matches: " + matches);

        // 既存のハッシュと比較
//        boolean matchesStored = encoder.matches(password, "$2a$10$8" 
//            + ".UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a"); // "password"
        boolean matchesStored = encoder.matches(password, "$2a$10$gGvN0Z" 
            + ".dxqN1Bhtxqk6I6uFyJwGzy9nwZuMSF9t3E3HUoXq6P6S4."); // "password123"
        System.out.println("Matches stored hash: " + matchesStored);
    }
}
