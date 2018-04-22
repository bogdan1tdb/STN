package com.stn.utils;

import javax.mail.internet.InternetAddress;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;

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

    public static boolean isEmpty(String ... args) {
        for (String arg : args) {
            if(arg.isEmpty() || arg.trim().length() == 0)
                return true;
        }
        return false;
    }

    public static boolean isName(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isURL(String url) {
        Matcher m;
        return url.matches("^(https?:\\/\\/)?(www\\.)?([\\w]+\\.)+[\u200C\u200B\\w]*.{2,128}\\/?$");
    }

    public static boolean between(String word, int min, int max) {
        if(word.length() < min || word.length() > max) {
            return false;
        } else {
            return  true;
        }
    }
}
