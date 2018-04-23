package structures.unit.vectors

import spock.lang.Shared
import spock.lang.Unroll
import structures.commons.DataStructure
import test.Spec
import structures.vectors.*

abstract class VectorSpec extends Spec {

    @Shared Vector<Object> vector

    @Unroll
    def "asString()" () {

        when:
        vector = (Vector<Integer>) constructor(input)

        // Reverse the string if it's some sort of stack
        if (isAssignableFrom(Stack, vector)) {
            input = input.reverse()
        }

        then:
        vector.toString() == input.toString()

        where:
        input           | _
        [1, 2, 3, 4, 5] | _
        [1, 2]          | _
        [1]             | _
        []              | _
    }

    @Unroll
    def "contains()"() {
        when:
        vector = (Vector<Integer>) constructor(input)

        then:
        vector.contains(value) == contains

        where:
        input           | value | contains
        [1, 2, 3, 4, 5] | 1     | true
        [1, 2]          | 0     | false
        [1]             | 1     | true
        []              | 1     | false
    }

    @Unroll
    def "remove() from non-empty vector"() {
        given:
        Integer value
        Integer v

        when:
        vector = (Vector<Integer>) constructor(input)

        value = vector.remove()

        // l for List, q for queue, etc.
        if (isAssignableFrom(Queue, vector)) {
            v = q
        } else {
            v = l
        }

        then:
        value == v

        where:
        input           | l | q | s
        [1, 2, 3, 4, 5] | 5 | 1 | 5
        [1, 2]          | 2 | 1 | 2
        [1]             | 1 | 1 | 1

    }

    @Unroll
    def "remove() from empty vector"() {
        when:
        vector = (Vector<Integer>) constructor()

        vector.remove()

        then:
        thrown DataStructure.EmptyDataStructureException
    }

    @Unroll
    def "toArray() object array"() {
        when:
        vector = (Vector<Integer>) constructor(input)


        // Reverse the string if it's some sort of stack
        if (isAssignableFrom(Stack, vector)) {
            input = input.reverse()
        }

        then:
        vector.toArray() == input.toArray()

        where:
        input           | _
        [1, 2, 3, 4, 5] | _
        [1, 2]          | _
        [1]             | _
        []              | _
    }

    @Unroll
    def "toArray() integer array"() {
        given:
        Integer[] a
        Integer[] b

        when:
        vector = (Vector<Integer>) constructor(input)

        // Reverse the string if it's some sort of stack
        if (isAssignableFrom(Stack, vector)) {
            input = input.reverse()
        }

        a = vector.toArray(new Integer[0])
        b = (Integer[]) input.toArray(new Integer[0])

        then:
        a == b
        Class.forName(a.getClass().getName()).isInstance(b)

        where:
        input           | _
        [1, 2, 3, 4, 5] | _
        [1, 2]          | _
        [1]             | _
        []              | _
    }

    @Unroll
    def "sameContents()"() {
        given:
        Vector<Integer> v1 = constructor(input1)
        Vector<Integer> v2 = constructor(input2)

        expect:
        v1.sameContents(v2) == same

        where:
        input1           | input2          | same
        [1, 2, 3, 4, 5]  | [1, 2, 3, 4, 5] | true
        [1, 2]           | [1, 2, 3]       | false
        [1]              | [1]             | true
        []               | []              | true
    }
}

final class VectorSpec_ArrayList extends VectorSpec {

    def setup() {
        myClass = ArrayList
    }
}

final class VectorSpec_ArrayStack extends VectorSpec {

    def setup() {
        myClass = ArrayStack
    }
}

final class VectorSpec_ArrayQueue extends VectorSpec {

    def setup() {
        myClass = ArrayQueue
    }
}

final class VectorSpec_LinkedList extends VectorSpec {

    def setup() {
        myClass = LinkedList
    }
}

final class VectorSpec_LinkedQueue extends VectorSpec {

    def setup() {
        myClass = LinkedQueue
    }
}

final class VectorSpec_LinkedStack extends VectorSpec {

    def setup() {
        myClass = LinkedStack
    }
}