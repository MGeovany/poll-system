package com.mgeovany.pollsystem.utils;

import java.security.SecureRandom;

public class GenerateRandomUrl {
   public static String generateUniqueUrl(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder uniqueUrl = new StringBuilder();

        for (int i = 0; i < length; i++) {
            uniqueUrl.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return uniqueUrl.toString();
    }
}
