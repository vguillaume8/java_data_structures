package util;

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
     * Determines whether two arrays are equal to each other. For them to
     * be equal, their length's must be equal, they must be of the same
     * class type, and each value in the ith position of each array must
     * be equal.
     *
     * @param a First array to compare
     * @param b Second array to compare
     * @return True if and only if the above conditions are met
     */
    @SuppressWarnings("unchecked")
    static boolean equals(Object[] a, Object[] b) {
        boolean equals = false;

        if (a.length == b.length) {

            // check the elements
            for (int i = 0; i < a.length; i++) {

                // If ith elements in both arrays are unequal
                if (!a[i].equals(b[i])) {
                    return false;
                }
            }

            equals = true;
        }

        return equals;
    }

    /**
     * Determines whether or not a {@code Comparable[]}  is sorted
     * in ascending order.
     *
     * @param array Array to verify.
     * @return True if and only if the above condition is met.
     */
    public static <E extends Comparable> boolean isSorted(E[] array) {

        for (int i = 0; i < array.length-1; i++) {

            // If ith value is greater than value at i+1,
            // then the array is not in strictly ascending order
            if (array[i].compareTo(array[i+1]) > 0) {
                return false;
            }
        }

        // If we got to the end,
        // the array is sorted
        return true;
    }

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
