package structures.auxiliary.interfaces;

/**
 * Convenience class for more readable code
 *
 * @param <T>
 */
public interface ComparableValue<T extends Comparable> extends Value<T>,  Comparable<ComparableValue<T>> {

    /**
     *
     * @param value
     * @return
     */
    boolean lessThan(ComparableValue<T> value);

    /**
     *
     * @param value
     * @return
     */
    boolean lessThanOrEqualTo(ComparableValue<T> value);

    /**
     *
     * @param value
     * @return
     */
    boolean greaterThan(ComparableValue<T> value);

    /**
     *
     * @param value
     * @return
     */
    boolean greaterThanOrEqualTo(ComparableValue<T> value);
}
