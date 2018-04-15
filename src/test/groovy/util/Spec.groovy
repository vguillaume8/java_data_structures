package util

import spock.lang.Specification

abstract class Spec extends Specification {

    /**
     * Class that will be tested
     */
    Class myClass

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
