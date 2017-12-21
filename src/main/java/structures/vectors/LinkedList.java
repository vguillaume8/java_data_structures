package structures.vectors;

import structures.auxiliary.LinkedListNode;

public abstract class LinkedList<T> extends Vector<T> {

    /**
     * Get Node at a specified index
     *
     * @param index Specified index
     * @return Node pointer at specified index
     */
    protected abstract LinkedListNode<T> getNode(int index);

    /**
     * Sets head node pointer to null
     */
    protected abstract void nullHead();

    /**
     *
     */
    @Override
    protected void init() {
        this.nullHead();
        super.init();
    }

    protected void init(T[] values) {
        this.nullHead();
        super.init(values);
    }

    protected void init(int length, T value) {
        this.nullHead();
        super.init(length, value);
    }
}


