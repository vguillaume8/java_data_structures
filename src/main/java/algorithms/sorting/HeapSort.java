package algorithms.sorting;

import algorithms.commons.Output;

public final class HeapSort<E extends Comparable<E> > extends SortingAlgorithm<E> {

    /**
     * Constructs InsertionSort object.
     */
    public HeapSort() {
        super();

        functions.put(DEFAULT, HeapSort::heapSort);
    }

    /**
     *
     * @param a
     * @param <E>
     * @return
     */
    public static <E extends Comparable> Output heapSort(E[] a, Output output) {

        return null;
    }

}
