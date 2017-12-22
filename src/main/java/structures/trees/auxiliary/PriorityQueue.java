package structures.trees.auxiliary;

import structures.auxiliary.DataStructure;

/**
 *
 * @author Jabari Dash
 * @param <Comparable> All values must be comparable
 */
public abstract class PriorityQueue<Comparable> implements DataStructure<Comparable> {

    // Array for which the values will be store
    Comparable[] values;

    /**
     * Returns the index of the parent of a specified index
     *
     * @param index Index for which it's parent index will be returned
     * @return Index of parent of provided index
     */
    public int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the index of the left child of a specified index
     *
     * @param index Index for which it's left child will be returned
     * @return Index of left child of provided index
     */
    public int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the index of the right child of a specified index
     *
     * @param index Index for which it's right child will be returned
     * @return Index of right child of provided index
     */
    public int RightChild(int index) {
        return 2 * index + 2;
    }
}
