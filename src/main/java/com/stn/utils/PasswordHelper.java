package com.stn.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

//Clasa pentru hashingul datelor
/*

Cum se utilizeaza :

! Nu se face hashing pana nu avem un salt setat !

1. Se seteaza salt-ul (sau se genereaza)

PasswordHelper passwordHelper = new PasswordHelper();
passwordHelper.generateSalt();

sau

PasswordHelper passwordHelper = new PasswordHelper();
passwordHelper.setSalt(salt); // salt-ul trebuie luat de undeva (baza de date,etc.)

2. Se face hashing la cuvant (word)

String hashedWord = passwordHelper.getPassword(word); // word -> cuvatul pe care vrem sa facem hashing (String)

3. Se salveaza salt-ul undeva (In caz ca stim deja salt-ul,nu mai trebuie salvat)

String hashedWordSalt = passwordHelper.getSalt();

// Este foarte important sa stim salt-ul,acesta trebuie sa fie unic pentru fiecare cuvant
// Procedeul nu este reversibil (chiar daca stim salt-ul)
// Pentru a verifica daca doua hashuri sunt egale se repeta pasii de mai sus,doar ca la primul pas nu se genereaza un salt
// ci se seteaza ca fiind saltul de la cuvantul deja hashuit

Ex (continuare)

passwordHelper.setSalt(hashedWordSalt);
String hashedWord2 = passwordHelper.getPassword(word2);

//Si verificam cu

hashedWord.equals(hashedWord2);

 */
public class PasswordHelper {

    private byte[] salt;

    public String getPassword(String passwordToHash) throws NoSuchAlgorithmException
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public void generateSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        this.salt = salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSalt() {
        return salt;
    }
}
