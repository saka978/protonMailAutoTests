package protonAutotests.utils;

import java.util.Random;

public class StringUtils {
    static Random rand = new Random();

    public static int generateRandomNumber(){
        return rand.nextInt(1000);
    }
}
