package algorithms.commons;

import java.util.HashMap;

/**
 * Object that encapsulates data
 * relevant to the runtime of an
 * algorithm. This data will be plotted
 * and used to verify runtime analysis.
 *
 * @author Jabari Dash
 */
public class Output {

    /**
     * Number of data comparisons
     */
    public int comparisons;

    /**
     * Number of recursive method calls
     */
    public int methodCalls;

    /**
     * Number of data swaps
     */
    public int dataSwaps;

    /**
     * Combines stats from two Output objects
     *
     * @param first First Output object
     * @param second Second Output object
     * @return Combined result
     */
    public static Output join(Output first, Output second) {

        Output output = new Output();

        output.comparisons = first.comparisons + second.comparisons;
        output.methodCalls = first.methodCalls + second.methodCalls;
        output.dataSwaps   = first.dataSwaps   + second.dataSwaps;

        return output;
    }

    /**
     * Combines this Output object with another
     *
     * @param output Other Output object
     * @return Combined result
     */
    public Output join(Output output) {

        return join(this, output);
    }

    /**
     * Returns HashMap representation
     * of the Output object.
     *
     * @return HashMap
     */
    public HashMap<String, Number> toHashMap() {

        HashMap<String, Number> map = new HashMap<>();

        map.put("comparisons", comparisons);
        map.put("methodCalls", methodCalls);
        map.put("dataSwaps", dataSwaps);

        return map;
    }

    /**
     *  Returns String representation
     *  of the Output object.
     *
     * @return String
     */
    @Override
    public String toString() {

        return this.toHashMap().toString();
    }
}
