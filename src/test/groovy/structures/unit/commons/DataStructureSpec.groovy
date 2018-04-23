package structures.unit.commons

import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Unroll
import structures.commons.DataStructure
import structures.vectors.ArrayList
import structures.vectors.ArrayQueue
import structures.vectors.ArrayStack
import structures.vectors.LinkedList
import structures.vectors.LinkedQueue
import structures.vectors.LinkedStack
import structures.trees.BinarySearchTree
import structures.trees.AVLTree
import test.Spec

abstract class DataStructureSpec extends Spec {

    @Shared DataStructure<Integer> structure

    @Unroll
    def "empty()"() {
        when:
        structure = (DataStructure<Integer>) constructor()

        then:
        structure.empty()
    }

    @Unroll
    def "contains()"() {
        when:
        structure = (DataStructure<Integer>) constructor(input)

        then:
        structure.contains(value) == contains

        where:
        input           | value | contains
        [1, 2, 3, 4, 5] | 7     | false
        [1, 2]          | 1     | true
        [1]             | 0     | false
        []              | 0     | false
    }

    @Unroll
    def "equivalentTo()"() {
        given:
        DataStructure<Integer> a
        DataStructure<Integer> b

        when:
        a = (DataStructure<Integer>) constructor(input1)
        b = (DataStructure<Integer>) constructor(input2)

        then:
        a.equivalentTo(b) == equivalent

        where:
        input1          | input2          | equivalent
        [1, 2, 3, 4, 5] | [1, 2, 3, 4, 5] | true
        [1, 2, 3, 4, 5] | [5, 2, 3, 4, 1] | false
        [1, 2, 3, 4, 5] | [1, 2, 3, 4]    | false
        [1, 2]          | [1, 2]          | true
        [1, 2]          | [2, 1]          | false
        [1]             | []              | false
        []              | []              | true
    }

    @Unroll
    def "indexOutOfBounds()"() {
        when:
        structure = (DataStructure<Integer>) constructor(input)

        then:
        structure.indexOutOfBounds(index) == out

        where:
        input           | index | out
        [1, 2, 3, 4, 5] | 0     | false
        [1, 2, 3, 4, 5] | -1    | true
        [1, 2, 3, 4, 5] | 7     | true
        [1, 2]          | 1     | false
        [1, 2]          | 2     | true
        [1]             | 0     | false
        []              | 0     | true
    }

    @Unroll
    def "insert()"() {
        when:
        structure = (DataStructure<Integer>) constructor(input)

        structure.insert(value)

        then:
        structure.size()          == input.size() + 1
        structure.contains(check) == contains

        where:
        input           | value | check | contains
        [1, 2, 3, 4, 5] | 6     | 6     | true
        [1, 2, 3, 4, 5] | 0     | 8     | false
        [1, 2]          | 3     | 3     | true
        [1]             | 0     | 9     | false
        []              | 1     | 1     | true
    }

    @Unroll
    def "insert(T[] values)"() {
        given:
        boolean contains = true

        when:
        structure = (DataStructure<Integer>) constructor()

        structure.insert(input as Integer[])

        for (Integer i : input) {

            if (!structure.contains(i)) {
                contains = false
                break
            }
        }

        then:
        structure.size() == input.size()
        contains

        where:
        input           | _
        [1, 2, 3, 4, 5] | _
        [1, 2]          | _
        [1]             | _
        []              | _
    }

    @Unroll
    def "insert(Collection<T> values)"() {
        given:
        boolean contains = true

        when:
        structure = (DataStructure<Integer>) constructor()

        structure.insert(input)

        for (Integer i : input) {

            if (!structure.contains(i)) {
                contains = false
                break
            }
        }

        then:
        structure.size() == input.size()
        contains

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
        DataStructure<Integer> a
        DataStructure<Integer> b

        when:
        a = (DataStructure<Integer>) constructor(input1)
        b = (DataStructure<Integer>) constructor(input2)

        then:
        a.sameContents(b) == sameContents

        where:
        input1          | input2          | sameContents
        [1, 2, 3, 4, 5] | [1, 2, 3, 4, 5] | true
        [1, 2, 3, 4, 5] | [5, 2, 3, 4, 1] | false
        [1, 2, 3, 4, 5] | [1, 2, 3, 4]    | false
        [1, 2]          | [1, 2]          | true
        [1, 2]          | [2, 1]          | false
        [1]             | []              | false
        []              | []              | true

    }

    @Unroll
    def "sameType()"() {
        given:
        DataStructure<Integer> a
        DataStructure<Integer> b

        when:
        a = (DataStructure<Integer>) constructor()
        b = (DataStructure<Integer>) constructor()

        then:
        a.sameType(b)
    }

    @Unroll
    def "size()"() {

        when:
        structure = (DataStructure<Integer>) constructor(input)

        then:
        structure.size() == input.size()

        where:
        input           | _
        [1, 2, 3, 4, 5] | _
        [1, 2]          | _
        [1]             | _
        []              | _
    }

    @Unroll
    def "verifyIndex() on non-empty list, don't expect exception"() {

        when:
        structure = (DataStructure<Integer>) constructor(input)

        structure.verifyIndex(index)

        then:
        noExceptionThrown()

        where:
        input           | index
        [1, 2, 3, 4, 5] | 3
        [1, 2]          | 0
        [1]             | 0
    }

    @Unroll
    def "verifyIndex() on non-empty list, expect exception"() {

        when:
        structure = (DataStructure<Integer>) constructor(input)

        structure.verifyIndex(index)

        then:
        thrown IndexOutOfBoundsException

        where:
        input           | index
        [1, 2, 3, 4, 5] | 10
        [1, 2]          | -1
        [1]             | 1
    }

    @Unroll
    def "verifyIndex() on empty list, expect exception"() {

        when:
        structure = (DataStructure<Integer>) constructor([])

        structure.verifyIndex(0)

        then:
        thrown DataStructure.EmptyDataStructureException
    }
}

final class DataStructureSpec_ArrayList extends DataStructureSpec {

    def setup() {
        myClass = ArrayList
    }
}

final class DataStructureSpec_ArrayStack extends DataStructureSpec {

    def setup() {
        myClass = ArrayStack
    }
}

final class DataStructureSpec_ArrayQueue extends DataStructureSpec {

    def setup() {
        myClass = ArrayQueue
    }
}

final class DataStructureSpec_LinkedList extends DataStructureSpec {

    def setup() {
        myClass = LinkedList
    }
}

final class DataStructureSpec_LinkedQueue extends DataStructureSpec {

    def setup() {
        myClass = LinkedQueue
    }
}

final class DataStructureSpec_LinkedStack extends DataStructureSpec {

    def setup() {
        myClass = LinkedStack
    }
}

@Ignore
final class DataStructureSpec_BinarySearchTree extends DataStructureSpec {

    def setup() {
        myClass = BinarySearchTree
    }
}

@Ignore
final class DataStructureSpec_AVLTree extends DataStructureSpec {

    def setup() {
        myClass = AVLTree
    }
}