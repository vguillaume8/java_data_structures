package util;

import java.util.concurrent.ThreadLocalRandom;

public class Util {


    /**
     * Returns random integer between min and max,
     * both inclusive.
     *
     * @param min Minimum value.
     * @param max Maximum value.
     * @return Random value.
     */
    public static int rand(int min, int max) {

        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
