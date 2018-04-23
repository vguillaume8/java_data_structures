package algorithms.sorting;

/**
 * Implementation of insertion sort.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class InsertionSort<E extends Comparable<E>> extends SortingAlgorithm<E> {

    /**
     * Constructs InsertionSort object.
     */
    public InsertionSort() {
        super();

        functions.put(DEFAULT, InsertionSort::insertionSort);
    }

    /**
     * Insertion sort for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    public static <E extends Comparable<E>> int insertionSort(E[] a) {
        int c = 0;

        int j;

        for (int i = 0; i < a.length; i++) {

            j = i;

            while (j > 0 && a[j-1].compareTo(a[j]) > 0) {
                c++;

                swap(a, j-1, j);
                j--;
            }
        }

        return c;
    }

}
