package structures.auxiliary.classes.concrete;

import structures.auxiliary.interfaces.ComparableValue;

public class ComparableNode<T extends Comparable> extends Node<T> implements ComparableValue<T> {

    public ComparableNode(T value, ComparableNode<T> prev, ComparableNode<T> next) {
        super(value, prev, next);
    }

    /**
     *
     * @param comparableValue
     * @return
     */
    @Override
    public int compareTo(ComparableValue<T> comparableValue) {
        return this.value().compareTo(comparableValue.value());
    }

    @Override
    public boolean lessThan(ComparableValue<T> value) {
        return this.compareTo(value) < 0 ? true : false;
    }

    @Override
    public boolean lessThanOrEqualTo(ComparableValue<T> value) {
        return this.compareTo(value) <= 0 ? true : false;
    }

    @Override
    public boolean greaterThan(ComparableValue<T> value) {
        return this.compareTo(value) > 0 ? true : false;
    }

    @Override
    public boolean greaterThanOrEqualTo(ComparableValue<T> value) {
        return this.compareTo(value) >= 0 ? true : false;
    }
}
