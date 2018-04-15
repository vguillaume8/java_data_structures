package structures.unit.vectors

import spock.lang.Shared
import spock.lang.Unroll
import structures.commons.DataStructure
import structures.vectors.List
import structures.vectors.LinkedList
import structures.vectors.ArrayList
import util.Spec

abstract class MyListSpec extends Spec {

    @Shared List<Object> list

    @Unroll
    def "Construct empty list with default constructor"() {
        when:
        list = (List<Integer>) constructor()

        then:
        list.empty()
        list.toString() == "[]"
        list.size()     == 0
    }

    @Unroll
    def "Construct non-empty list from array"() {
        when:
        list =  (List<Integer>) constructor(input as Object[])

        then:
        list.toString() == string
        list.size()     == size
        list.empty()    == empty

        where:
        input           | string            | size | empty
        [1, 2, 3, 4, 5] | "[1, 2, 3, 4, 5]" | 5    | false
        [1, 2]          | "[1, 2]"          | 2    | false
        [1]             | "[1]"             | 1    | false
        []              | "[]"              | 0    | true
    }

    @Unroll
    def "Construct non-empty list from collection"() {
        when:
        list = (List<Integer>) constructor(input)

        then:
        list.toString() == string
        list.size()     == size
        list.empty()    == empty

        where:
        input           | string            | size | empty
        [1, 2, 3, 4, 5] | "[1, 2, 3, 4, 5]" | 5    | false
        [1, 2]          | "[1, 2]"          | 2    | false
        [1]             | "[1]"             | 1    | false
        []              | "[]"              | 0    | true
    }

    @Unroll
    def "List equality"() {
        when:
        List<Integer> l1 = (List<Integer>) constructor(input1)
        List<Integer> l2 = (List<Integer>) constructor(input2)

        then:
        (l1 == l2) == equals

        where:
        input1          | input2           | equals
        []              | []               | true
        [1, 2, 3, 4, 5] | [1, 2, 3, 4, 5]  | true
        [1, 2]          | [1, 2]           | true
        [1]             | [2]              | false
        []              | [1]              | false
        [1, 2, 3]       | [3, 2, 1]        | false
    }

    @Unroll
    def "Insert at the front of list"() {
        when:
        list = (List<Integer>) constructor()

        for (Integer i : input) {
            list.insertFirst(i)
        }

        then:
        list.toString() == string
        list.size()     == size
        list.empty()    == empty

        where:
        input           | string            | size | empty
        [1, 2, 3, 4, 5] | "[5, 4, 3, 2, 1]" | 5    | false
        [1, 2]          | "[2, 1]"          | 2    | false
        [1]             | "[1]"             | 1    | false
        []              | "[]"              | 0    | true
    }

    @Unroll
    def "Insert at the back of list"() {
        when:
        list = (List<Integer>) constructor()

        for (Integer i : input) {
            list.insertLast(i)
        }

        then:
        list.toString() == string
        list.size()     == size
        list.empty()    == empty

        where:
        input           | string            | size | empty
        [1, 2, 3, 4, 5] | "[1, 2, 3, 4, 5]" | 5    | false
        [1, 2]          | "[1, 2]"          | 2    | false
        [1]             | "[1]"             | 1    | false
        []              | "[]"              | 0    | true
    }

    @Unroll
    def "Insert a value at arbitrary index in list"() {
        when:
        list = (List<Integer>) constructor(input)

        then:
        list.insert(value, index)

        where:
        input           | string                | size | empty | value  | index
        [1, 2, 3, 4, 5] | "[1, 2, 3, -1, 4, 5]" | 6    | false | -1     | 3
        [1, 2]          | "[1, -1, 2]"          | 3    | false | -1     | 1
        [1]             | "[-1, 1]"             | 2    | false | -1     | 0
        []              | "[-1]"                | 1    | false | -1     | 0
    }

    @Unroll
    def "Remove from non-empty list"() {
        when:
        list = (List<Integer>) constructor(input)
        list.remove()

        then:
        list.size()     == size
        list.toString() == string
        list.empty()    == empty

        where:
        input           | string         | size | empty
        [1, 2, 3, 4, 5] | "[1, 2, 3, 4]" | 4    | false
        [1, 2]          | "[1]"          | 1    | false
        [1]             | "[]"           | 0    | true
    }

    @Unroll
    def "Remove from back of non-empty list"() {
        when:
        list = (List<Integer>) constructor(input)
        list.removeLast()

        then:
        list.size()     == size
        list.toString() == string
        list.empty()    == empty

        where:
        input           | string         | size | empty
        [1, 2, 3, 4, 5] | "[1, 2, 3, 4]" | 4    | false
        [1, 2]          | "[1]"          | 1    | false
        [1]             | "[]"           | 0    | true
    }

    @Unroll
    def "Remove from front of non-empty list"() {
        when:
        list = (List<Integer>) constructor(input)
        list.removeFirst()

        then:
        list.size()     == size
        list.toString() == string
        list.empty()    == empty

        where:
        input           | string         | size | empty
        [1, 2, 3, 4, 5] | "[2, 3, 4, 5]" | 4    | false
        [1, 2]          | "[2]"          | 1    | false
        [1]             | "[]"           | 0    | true
    }

    @Unroll
    def "Remove from an empty list"() {
        when:
        list = (List<Integer>) constructor(input)
        list.remove()

        then:
        thrown DataStructure.EmptyDataStructureException

        where:
        input | _
        []    | _
    }

    @Unroll
    def "Remove from arbitrary index"() {
        when:
        list = (List<Integer>) constructor(input)
        list.remove(index)

        then:
        list.size()     == size
        list.toString() == string
        list.empty()    == empty

        where:
        input           | string         | size | empty | index
        [1, 2, 3, 4, 5] | "[1, 2, 4, 5]" | 4    | false | 2
        [1, 2]          | "[1]"          | 1    | false | 1
        [1]             | "[]"           | 0    | true  | 0
    }

    @Unroll
    def "Access value at specified index in non-empty list"() {
        when:
        list = (List<Integer>) constructor(input)

        then:
        list.get(index) == input[index]

        where:
        input           | string            | index
        [1, 2, 3, 4, 5] | "[1, 2, 3, 4, 5]" | 4
        [1, 2]          | "[1, 2]"          | 1
        [1]             | "[1]"             | 0
    }

    @Unroll
    def "Access value at index in empty list"() {
        when:
        list = (List<Integer>) constructor()

        list.get(0)

        then:
        thrown DataStructure.EmptyDataStructureException
    }

    @Unroll
    def "Update value at specified index"() {
        when:
        list = (List<Integer>) constructor(input)
        list.set(value, index)
        input[index] = value

        then:
        list.get(index) == input[index]
        list.toString() == input.toString()

        where:
        input           | index| value
        [1, 2, 3, 4, 5] | 4    | -1
        [1, 2]          | 1    | -1
        [1]             | 0    | -1
    }

    @Unroll
    def "Verify that the iterator works"() {
        given:
        boolean equals = true
        Iterator i1
        Iterator i2

        list = (List<Integer>) constructor(input)

        i1 = list.iterator()
        i2 = input.iterator()

        when:
        while (i1.hasNext() && i2.hasNext()) {
            if (i1.next() != i2.next()) {
                equals = false
                break
            }
        }

        if (i1.hasNext() || i2.hasNext()) {
            equal = false
        }

        then:
        equals

        where:
        input           | _
        [1, 2, 3, 4, 5] | _
        [1, 2]          | _
        [1]             | _
        []              | _

    }

    @Unroll
    def "toString()"() {
        when:
        list = (List<Integer>) constructor()
        list.insert(input)

        then:
        list.toString() == string

        where:
        input           | string
        [1, 2, 3, 4, 5] | "[1, 2, 3, 4, 5]"
        [1, 2]          | "[1, 2]"
        [1]             | "[1]"
        []              | "[]"
    }
}

class ListSpec_ArrayList extends MyListSpec {

    def setup() {
        myClass = ArrayList
    }
}

class ListSpec_LinkedList extends MyListSpec {

    def setup() {
        myClass = LinkedList
    }
}