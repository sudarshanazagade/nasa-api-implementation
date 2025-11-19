package com.openapi.nasa.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class to generate BCrypt password hashes
 * Use this to create password hashes for your users
 */
public class PasswordUtility {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Example: Generate hash for common passwords
        // You can change "password" to any password you want to use
        String password = "Sush69"; // Change this to your desired password
        
        String hashedPassword = encoder.encode(password);
        
        System.out.println("===========================================");
        System.out.println("Password: " + password);
        System.out.println("BCrypt Hash: " + hashedPassword);
        System.out.println("===========================================");
        System.out.println();
        System.out.println("To use this hash in your database:");
        System.out.println("UPDATE nasa_members SET pw = '{bcrypt}" + hashedPassword + "' WHERE user_id = 'guest';");
        System.out.println("or");
        System.out.println("UPDATE nasa_members SET pw = '{bcrypt}" + hashedPassword + "' WHERE user_id = 'admin';");
        System.out.println();
        System.out.println("Testing password verification:");
        boolean matches = encoder.matches(password, hashedPassword);
        System.out.println("Password matches: " + matches);
    }
}

