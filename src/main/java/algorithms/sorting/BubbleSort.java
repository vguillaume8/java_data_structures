package algorithms.sorting;

import java.util.function.Function;

/**
 * Implementations of Bubble sort.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class BubbleSort<E extends Comparable> extends SortingAlgorithm<E> {

    /**
     * Constructs bubble sort object.
     */
    public BubbleSort() {
        super();

        functions.put(DEFAULT, BubbleSort::optimized);
        functions.put(OPTIMIZED, BubbleSort::optimized);
        functions.put(SIMPLE, BubbleSort::simple);
    }

    /**
     *
     * @param a
     * @param <E>
     * @return
     */
    public static <E extends Comparable> int optimized(E[] a) {
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


    public static <E extends Comparable> int simple(E[] a) {
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
