package util

class Util {

    /**
     * Determines whether or not a {@code Comparable[]}  is sorted
     * in ascending order.
     *
     * @param array Array to verify.
     * @return True if and only if the above condition is met.
     */
    static boolean isSorted(Comparable[] array) {

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