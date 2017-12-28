package util;

import structures.commons.IndexedDataStructure;

import java.util.Arrays;

/**
 * Static functions for reuse throughout the library
 *
 * @author Jabari Dash
 */
public final class Util {

    /**
     * Returns the maximum between two integers.
     *
     * @param a First integer.
     * @param b Second integer.
     * @return The larger of the two.
     */
    @SuppressWarnings("unused")
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * Determines whether or not an {@code IndexedDataStructure} is
     * sorted in ascending order.
     *
     * @param vector Data structure to verify.
     * @return True if and only if the above condition is met.
     */
    @SuppressWarnings("unused")
    public static boolean isSorted(IndexedDataStructure<Comparable> vector) {

        int size = vector.size();

        for (int i = 0; i < size-1; i++) {
            if (vector.get(i).compareTo(vector.get(i+1)) > 0) {
                return false;
            }
        }

        return true;
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not a {@code Comparable[]}  is sorted
     * in ascending order.
     *
     * @param array Array to verify.
     * @return True if and only if the above condition is met.
     */
    public static boolean isSorted(Comparable[] array) {

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

//------------------------------------------------------------------------------

    /**
     * Wrapper around Java library array copy function.
     *
     * @param original Original array.
     * @param newLength Length of new array.
     * @param newType Type of new array.
     * @param <T> Generic type of new array.
     * @param <U> Generic type of original array.
     * @return Copied array.
     */
    public static <T, U> T[] ArrayCopy(U[] original, int newLength, Class<? extends T[]> newType) {
        return Arrays.copyOf(original, newLength, newType);
    }

//------------------------------------------------------------------------------

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

        if (array != null) {
            if (array.length > 0) {

                // Append all the values in the array to the back
                // but in between the brackets
                for (Object value : array) {
                    if (value == null)
                        stringBuilder.insert(stringBuilder.length()-1, "null" + ", ");

                    else
                        stringBuilder.insert(stringBuilder.length()-1, value.toString() + ", ");
                }

                int length = stringBuilder.length();

                // Get rid of the extra comma and space at the end
                stringBuilder.delete(length-3, length-1);
            }
        }

        return stringBuilder.toString();
    }

//------------------------------------------------------------------------------

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
    public static boolean equals(Object[] a, Object[] b) {
        boolean equals = false;

        if (a.length == b.length) {

            // check the values
            for (int i = 0; i < a.length; i++) {

                // If ith values in both arrays are unequal
                if (!a[i].equals(b[i])) {
                    return false;
                }
            }

            equals = true;

        }

        return equals;
    }

}
