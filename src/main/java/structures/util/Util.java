package structures.util;

import java.util.Arrays;

/**
 * Static functions for reuse throughout the library
 *
 * @author Jabari Dash
 */
public final class Util<T> {

    /**
     * Wrapper around Java library array copy function
     *
     * @param original
     * @param newLength
     * @param newType
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> T[] ArrayCopy(U[] original, int newLength, Class<? extends T[]> newType) {
        return Arrays.copyOf(original, newLength, newType);
    }

    /**
     * Returns String representation of a specified array
     *
     * @param array Array to be converted to String
     * @return String version of the array
     */
    public static String ArrayToString(Object[] array) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');
        stringBuilder.append(']');

        if (array.length > 0) {

            // Append all the values in the array to the back
            // but in between the brackets
            for (Object value : array) {
                stringBuilder.insert(stringBuilder.length()-1, value.toString() + ", ");
            }

            int length = stringBuilder.length();

            // Get rid of the extra comma and space at the end
            stringBuilder.delete(length-3, length-1);
        }

        return stringBuilder.toString();
    }

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
    public static boolean equals(Object[] a, Object[] b) {
        boolean equals = false;

        if (a.length == b.length) {

            if (a.getClass().getSimpleName().equals(b.getClass().getSimpleName())) {
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != b[i]) {
                        return false;
                    }
                }

                equals = true;
            }
        }

        return equals;
    }

}
