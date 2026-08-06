package com.tap.utility;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordGenerator {

    public static void main(String[] args) {

        String password = "TapFood@123";

        for (int i = 1; i <= 15; i++) {
            System.out.println(BCrypt.hashpw(password, BCrypt.gensalt(12)));
        }

    }

}
