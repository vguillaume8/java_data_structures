package structures.graphs;

import structures.commons.DataStructure;

public class Graph<K, V> implements DataStructure<K> {

    final boolean DIRECTED   = true;
    final boolean UNDIRECTED = !DIRECTED;
    final boolean WEIGHTED   = true;
    final boolean UNWEIGHTED = !WEIGHTED;

    private int size;

    public Graph(boolean weighted, boolean directed) {
        size = 0;

    }

    /**
     * Determines whether or not the DataStructure has a contains a specified key.
     *
     * @param key Specified key
     * @return True if and only if the specified value is within the Structure
     */
    @Override
    public boolean contains(K key) {
        return false;
    }

    /**
     * Inserts a specified value into the List.
     *
     * @param key The specified key to insert.
     * @return True if and only if the value was successfully inserted.
     */
    @Override
    public boolean insert(K key) {
        return false;
    }

    /**
     * Returns the number of nodes in the data structure.
     *
     * @return Number of nodes in the DataStructure.
     */
    @Override
    public int size() {
        return 0;
    }
}