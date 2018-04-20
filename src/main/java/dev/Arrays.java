package dev;

import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * Reusable methods associated with
 * arrays such as printing them, or
 * instantiating them in various ways.
 *
 * @author Jabari Dash
 */
public final class Arrays {

    /**
     *
     * @param array
     * @return
     */
    public static String toString(Object[] array) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        for (Object obj : array) {
            sj.add(obj.toString());
        }

        return sj.toString();
    }

    /**
     *
     * @param length
     * @param min
     * @param max
     * @param ordered
     * @param ascending
     * @return
     */
    public static Integer[] array(int length,
                                  int min,
                                  int max,
                                  boolean ordered,
                                  boolean ascending) {

        if (ordered) {

            if (!ascending) {

                // Descending
                return IntStream.range(min, max)
                                .map(i -> max - i + min - 1)
                                .boxed()
                                .toArray(Integer[]::new);
            }

            // Ascending
            return IntStream.range(min, length)
                            .boxed()
                            .toArray(Integer[]::new);

        } else {

            // Random
            return new Random().ints(min, max)
                               .limit(length)
                               .boxed()
                               .toArray(Integer[]::new);
        }
    }
}
