package com.gold_mining_app_backend.util;

import java.util.Random;

public class PasswordGenerator {
public static String generatePassword(int length) {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
    StringBuilder password = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < length; i++) {
        password.append(characters.charAt(random.nextInt(characters.length())));
    }
    return password.toString();
}
}
