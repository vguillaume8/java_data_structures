package algorithms.sorting;

/**
 * Implementations of Bubble sort.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class BubbleSort<E extends Comparable<E>> extends SortingAlgorithm<E> {

    /**
     * Constructs bubble sort object.
     */
    public BubbleSort() {
        super();

        functions.put("default", BubbleSort::bubbleSortOptimized);
        functions.put("optimized", BubbleSort::bubbleSortOptimized);
        functions.put("simple", BubbleSort::bubbleSortSimplified);
    }

    /**
     * Bubble Sort (optimized) for array of Comparable
     * objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic types.
     * @return Number of comparisons to complete the sort.
     */
    public static <E extends Comparable> int bubbleSortOptimized(E[] a) {
        int c = 0;

        boolean swapped;

        for (int i = 0; i < a.length; i++) {

            swapped = false;

            for (int j = 0; j < a.length - i -1 ; j++) {

                c++;

                if (a[j].compareTo(a[j+1]) > 0) {

                    swap(a, j, j+1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return c;
    }

    /**
     * Bubble Sort (simplified) for array of Comparable
     * objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic types.
     * @return Number of comparisons to complete the sort.
     */
    public static <E extends Comparable> int bubbleSortSimplified(E[] a) {
        int c = 0;

        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a.length - i -1 ; j++) {

                c++;

                if (a[j].compareTo(a[j+1]) > 0) {

                    swap(a, j, j+1);
                }
            }
        }

        return c;
    }
}
