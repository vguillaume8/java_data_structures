package test

import spock.lang.Specification

/**
 * Specification subclass that has
 * no actual tests. This class provides
 * several functions called constructor()
 * that use reflection to return an instance
 * of an object using one of three constructors.
 *
 * 1. The default
 * 2. Accepts T[] of values
 * 3. Accepts java Collection<T> of values
 *
 * This way, all Specs that are testing interfaces
 * or classes that share a common ancestry can
 * simply extend the spec that has the tests,
 * and the tests will be applied to both
 * subclasses.
 *
 * Example:
 * Two implementations of Stack that should
 * behave exactly the same is a good
 * candidate. That way, we do not need to
 * write the same exact tests twice. The internals
 * are different, but as far as the unit tests
 * are concerns, as long as the classes meet
 * the contract, they are good. The contract
 * is written once, and extended to however
 * many classes extend the Spec.
 *
 * NOTE: For classes like Trees, or Maps,
 * even though they may implement the same
 * interfaces, provided the nature of these
 * structures constitutes rearrangement of
 * internal data, this method may not be as
 * useful because the tests must be conscious
 * of this. And, it may require a more complex
 * test to the point where it's not longer
 * worth.
 *
 * @author Jabari dash
 */
abstract class Spec extends Specification {

    /**
     * Class that will be tested
     */
    Class myClass

    /**
     *
     * @param parent
     * @param child
     * @return
     */
    boolean isAssignableFrom(Class parent, Object child) {

        return parent.isAssignableFrom(child.getClass())
    }

    /**
     * Returns name of class that will be tested.
     *
     * @return Name of class that will be tested
     */
    String name() {

        return myClass.getName()
    }

    /**
     * Returns instance constructed from
     * default constructor for test class.
     *
     * @return
     */
    public Object constructor() {

        return Class.forName(name())
                    .getConstructor()
                    .newInstance()
    }

    /**
     * Returns instance from constructor
     * that takes an array of values as
     * a parameter.
     *
     * NOTE - Must put values in another
     * array because newInsance it understanding
     * each value in values array as a different
     * parameter to the constructor. This is a coincident
     * because this particular constructor happens
     * to also take an array. So we encapsulate it,
     * so the array appears as one argument, and falls
     * to the constructor that takes the array as a
     * single argument
     *
     * @param values
     * @return
     */
    public Object constructor(Object[] values) {
        Class[] args = [Object[].class] as Class[]

        return Class.forName(name())
                    .getConstructor(args)
                    .newInstance([values] as Object[])
    }

    /**
     * Returns instance from constructor
     * that takes a collection of values as
     * a parameter.
     *
     * @param values
     * @return
     */
    public Object constructor(Collection<Object> values) {
        Class[] args = [Collection.class] as Class[]


        return Class.forName(name())
                    .getConstructor(args)
                    .newInstance(values)
    }
}
