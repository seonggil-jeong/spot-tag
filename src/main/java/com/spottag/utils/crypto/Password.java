package com.spottag.utils.crypto;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Password {
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }

}
