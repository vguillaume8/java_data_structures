package structures.vectors

import spock.lang.Unroll
import spock.lang.Specification
import structures.util.interfaces.DataStructure.EmptyDataStructureException;

class ArrayListSpec extends Specification {

    @Unroll
    def "#Construct an empty list"() {
        setup:
        ArrayList list = new ArrayList()

        expect:
        list.size() == 0
        list.empty() == true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#Constructing a list with bad values"() {
        setup:
        ArrayList list

        when:
        if (value == null) {
            list = new ArrayList(value);
        } else {
            list = new ArrayList(value, "x")
        }

        then:
        thrown exception

        where:
        value | exception
        null  | NullPointerException
        -1    | IllegalArgumentException

    }

//------------------------------------------------------------------------------


    @Unroll
    def "#Construct a non-empty list from array"() {
        setup:
        ArrayList list = new ArrayList(values)

        expect:
        list.size() == length
        list.empty() == empty

        where:
        values                 | length | empty
        [1, 2, 3] as Integer[] | 3      | false
        [1] as Integer[]       | 1      | false
        [] as Integer[]        | 0      | true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#Construct a non-empty list by length"() {
        setup:
        ArrayList list = new ArrayList(length, "x")

        expect:
        list.size() == length
        list.empty() == empty

        where:
        length | empty
        3      | false
        1      | false
        0      | true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#contains()"() {
        setup:
        ArrayList list = new ArrayList(values)

        expect:
        list.contains(1) == contains

        where:
        values                 | contains
        [1, 2, 3] as Integer[] | true
        [2, 2, 3] as Integer[] | false
        [1] as Integer[]       | true
        [] as Integer[]        | false
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#get(index) with good data"() {
        setup:
        ArrayList list = new ArrayList(values)

        when:
        Object x = list.get(index)

        then:
        x == value

        where:
        values                 | index | value
        [1, 2, 3] as Integer[] | 0     | 1
        [1] as Integer[]       | 0     | 1
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#get(index) with bad data"() {
        setup:
        ArrayList list = new ArrayList(values)

        when:
        Object x = list.get(index)

        then:
        thrown IndexOutOfBoundsException

        where:
        values                 | index
        [1, 2, 3] as Integer[] | 4
        [] as Integer[]        | 0
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#insert()"() {
        setup:
        ArrayList list = new ArrayList()

        when:
        list.insert(value)

        then:
        list.contains(check) == contains

        where:
        value | check | contains
        1     | 1     | true
        1     | 2     | false
        null  | 2     | false
        null  | null  | true
        "1"   | 1     | false
        "1"   | "1"   | true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#insertFirst()"() {
        setup:
        ArrayList list = new ArrayList(values);

        when:
        list.insertFirst(value)

        then:
        list.get(0) == value

        where:
        values                 | value
        [1, 2, 3] as Integer[] | 4
        [1, 2, 3] as Integer[] | "1"
        [1, 2, 3] as Integer[] | null
        [] as Integer[]        | 1
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#insertLast()"() {
        setup:
        ArrayList list = new ArrayList(values);

        when:
        list.insertLast(value)

        then:
        list.get(list.size()-1) == value

        where:
        values                 | value
        [1, 2, 3] as Integer[] | 4
        [1, 2, 3] as Integer[] | "1"
        [1, 2, 3] as Integer[] | null
        [] as Integer[]        | 1
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#insert(value, index)"() {
        setup:
        ArrayList list = new ArrayList(values);

        when:
        list.insert(value, index)

        then:
        list.get(index) == value

        where:
        values                 | value | index
        [1, 2, 3] as Integer[] | 4     | 2
        [1, 2, 3] as Integer[] | "1"   | 2
        [1, 2, 3] as Integer[] | null  | 0
        [] as Integer[]        | 1     | 0
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#empty() when initializing with an array"() {
        setup:
        ArrayList list = new ArrayList(values);

        expect:
        list.empty() == empty

        where:
        values                 | empty
        [1, 2, 3] as Integer[] | false
        [] as Integer[]        | true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#empty() when initializing with specified length"() {
        setup:
        ArrayList list = new ArrayList(length, "x");

        expect:
        list.empty() == empty

        where:
        length | empty
        1      | false
        10     | false
        0      | true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#empty() when using basic constructor"() {
        setup:
        ArrayList list = new ArrayList();

        expect:
        list.empty() == empty

        where:
        empty = true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#remove(index) with valid index"() {
        setup:
        ArrayList list = new ArrayList(values)

        when:
        def r = list.remove(index)

        then:
        list.contains(check) == false
        list.size() == length - 1
        r == removed

        where:
        values                 | index | check  | length | removed
        [1, 2, 3] as Integer[] | 0     | 1      | 3      | 1
        [1, 2, 3] as Integer[] | 1     | 2      | 3      | 2
        [1] as Integer[]       | 0     | 1      | 1      | 1
    }

    @Unroll
    def "#remove(index) with invalid input"() {
        setup:
        ArrayList list = new ArrayList(values)

        when:
        list.remove(index)

        then:
        thrown exception

        where:
        values                 | index | exception
        [1, 2, 3] as Integer[] | 4     | IndexOutOfBoundsException
        [] as Integer[]        | 0     | EmptyDataStructureException
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#remove()"() {
        setup:
        ArrayList list = new ArrayList(values);

        when:
        while (removals > 0) {
            list.remove()
            removals--;
        }

        then:

        if (list.size() > 0) {
            assert list.get(list.size()-1) == value

        } else {
            assert list.empty() == true
        }

        where:
        values                 | value | removals
        [1, 2, 3] as Integer[] | 1     | 2
        [1, 2, 3] as Integer[] | 2     | 1
        [1, 2]    as Integer[] | null  | 2
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#removeFirst()"() {
        setup:
        ArrayList list = new ArrayList(values);

        when:
        while (removals > 0) {
            list.removeFirst()
            removals--;
        }

        then:

        if (list.size() > 0) {
            assert list.get(0) == value

        } else {
            assert list.empty() == true
        }

        where:
        values                 | value | removals
        [1, 2, 3] as Integer[] | 3     | 2
        [1, 2, 3] as Integer[] | 2     | 1
        [1, 2]    as Integer[] | null  | 2
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#removeLast()"() {
        setup:
        ArrayList list = new ArrayList(values);

        when:
        while (removals > 0) {
            list.removeLast()
            removals--;
        }

        then:

        if (list.size() > 0) {
            assert list.get(list.size() - 1) == value

        } else {
            assert list.empty() == true
        }

        where:
        values                 | value | removals
        [1, 2, 3] as Integer[] | 1     | 2
        [1, 2, 3] as Integer[] | 2     | 1
        [1, 2]    as Integer[] | null  | 2
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#toString()" () {
        setup:
        ArrayList list = new ArrayList(values)

        when:
        String s = list.toString()

        then:
        s == string

        where:
        values                      | string
        [1, 2, 3] as Integer[]      | "1, 2, 3"
        ["1", "2", "3"] as String[] | "1, 2, 3"
        [] as Object[]              | ""
    }
}
