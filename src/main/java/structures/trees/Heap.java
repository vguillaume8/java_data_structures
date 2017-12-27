package structures.trees;

public final class Heap {


    /**
     * Determines whether or not an array represents a complete binary search tree.
     *
     * TODO - Maybe I could use this?
     *
     * @param keys Array representation of complete binary tree with keys in level order
     * @param <K> Key type (Comparable)
     * @return True if and only if the above condition is met.
     */
    public static <K extends Comparable> boolean isBinarySearchTree(K[] keys) {
        int left;
        int right;
        int compL;
        int compR;

        // Loop through array, checking that
        // all left children are less than parent,
        // and all right children are greater than parent
        for (int i = 0; i < keys.length; i++) {

            // Compute children indexes
            left = (2 * i) + 1;
            right = (2 * i) + 2;

            // Make sure left child is less than ith key
            if (left >= 0 && left < keys.length) {

                compL = keys[left].compareTo(keys[i]);

                if (compL >= 0) {
                    return false;
                }
            }

            // Make sure right child is greater than ith key
            if (right >= 0 && right < keys.length) {

                compR = keys[right].compareTo(keys[i]);

                if (compR <= 0) {
                    return false;
                }

            }

        }

        return true;
    }

}
