package dev;

public class Main {

    /**
     *
     * @param array
     */
    protected static void println(Object[] array) {
        println(Arrays.toString(array));
    }

    /**
     *
     * @param array
     */
    protected static void print(Object[] array) {
        print(Arrays.toString(array));
    }

    /**
     * Wrapper around {@code System.out.println()}
     * for less verbose syntax
     *
     * @param obj Object to print
     */
    protected static void println(Object obj) {

        System.out.println(obj);
    }

    /**
     * Wrapper around {@code System.out.print()}
     * for less verbose syntax
     *
     * @param obj Object to print
     */
    protected static void print(Object obj) {

        System.out.print(obj);
    }
}
