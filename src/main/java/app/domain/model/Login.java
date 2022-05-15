package app.domain.model;

import app.domain.shared.Constants;

import java.security.SecureRandom;
import java.util.Random;

public class Login {

    public String id;
    public String password;


    public Login(String id){
        this.id=id;
        password= generatePwd(Constants.PWD_LENGHT);
    }

    /**@author Jose Martinez from stackoverFlow
     * @link https://stackoverflow.com/a/51823845
     * This method generates a password with 7 alphanumeric characters,
     * including three capital letters and two digits.
     * The code was modifed to accomedate clients requirements
     * @param lenght of password
     * @return password with 7 alphanumeric characters, including three capital letters and two digits
     */
    static public String generatePwd(int lenght) {

        char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] NUMBERS = "0123456789".toCharArray();
        char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random rand = new SecureRandom();

        char[] password = new char[lenght];

        //get the requirements out of the way
        password[0] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[2] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[3] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[4] = NUMBERS[rand.nextInt(NUMBERS.length)];

        //populate rest of the password with random chars
        for (int i = 5; i < lenght; i++) {
            password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        //shuffle it up
        for (int i = 0; i < password.length; i++) {
            int randomPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randomPosition];
            password[randomPosition] = temp;
        }
        return new String(password);
    }
}
