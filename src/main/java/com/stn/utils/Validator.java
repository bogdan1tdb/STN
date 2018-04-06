package com.stn.utils;

import javax.mail.internet.InternetAddress;

public class Validator {

    public static boolean isEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty (String ... args) {
        for (String arg : args) {
            if(arg.isEmpty())
                return true;
        }
        return false;
    }

    public static boolean between (String word, int min, int max) {
        if(word.length() < min || word.length() > max) {
            return false;
        } else {
            return  true;
        }
    }
}
