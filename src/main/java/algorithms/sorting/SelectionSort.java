package algorithms.sorting;

public final class SelectionSort<E extends Comparable <E>> extends SortingAlgorithm<E> {

    /**
     * Constructs InsertionSort object.
     */
    public SelectionSort() {
        super();

        functions.put(DEFAULT, SelectionSort::selectionSort);
    }

    /**
     * Selection sort for Comparable objects.
     *
     * @param a Array of comparable objects.
     * @param <E> Generic type
     * @return Pointer to sorted array
     */
    public static <E extends Comparable> int selectionSort(E[] a) {
        int c;
        int min;

        c = 0;

        for (int i = 0; i < a.length; i++) {

            min = i;

            for (int j = i; j < a.length; j++) {

                c++;

                if (a[j].compareTo(a[min]) < 0) {

                    min = j;
                }
            }

            if (min != i) {

                swap(a, min, i);
            }
        }

        return c;
    }
}
