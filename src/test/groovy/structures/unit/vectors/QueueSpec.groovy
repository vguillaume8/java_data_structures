package structures.unit.vectors

import spock.lang.Shared
import spock.lang.Unroll
import structures.commons.DataStructure
import structures.vectors.Queue
import structures.vectors.ArrayQueue
import structures.vectors.LinkedQueue
import util.Spec

abstract class QueueSpec<T> extends Spec {

    @Shared Queue<Object> queue;

    @Unroll
    def "Construct an empty queue from default constructor"() {
        when:
        queue = (Queue<Integer>) constructor()

        then:
        queue.empty()
        queue.size()     == 0
        queue.toString() == "[]"
    }

    @Unroll
    def "Construct a queue from an array of values"() {
        when:
        queue = (Queue<Integer>) constructor()
        queue.insert(input)
        Queue<Integer> s = constructor(input as Integer[])

        then:
        queue == s

        where:
        input        | _
        [1, 2, 3, 4] | _
        [1, 2]       | _
        [1]          | _
        []           | _

    }

    @Unroll
    def "Construct a queue from a Java collection of values"() {
        when:
        queue = (Queue<Integer>) constructor()
        queue.insert(input)

        Queue<Integer> s = constructor(input)

        then:
        queue == s

        where:
        input        | _
        [1, 2, 3, 4] | _
        [1, 2]       | _
        [1]          | _
        []           | _

    }

    @Unroll
    def "Check equality between queues by content"() {

        when:
        Queue<Object> first  = constructor(input1)
        Queue<Object> second = constructor(input2)

        then:
        first.equals(second) == equals

        where:
        input1          | input2          | equals
        [1, 2, 3, 4, 5] | [1, 2, 3, 4, 5] | true
        [1, 2, 3, 4, 5] | [1, 2, 3, 4]    | false
        []              | []              | true
    }

    @Unroll
    def "Test enqueue() method"() {
        given:
        queue = (Queue<Integer>) constructor()

        when:
        for (Integer i : input) {
            queue.enqueue(i)
        }

        then:
        queue.empty()    == empty
        queue.size()     == size
        queue.toString() == string

        where:
        size | empty ||string             || input
        5    | false || "[5, 4, 3, 2, 1]" || [5, 4, 3, 2, 1]
        2    | false || "[1, 2]"          || [1, 2]
        0    | true  || "[]"              || []
    }

    @Unroll
    def "Test dequeue() method on non-empty queue"() {
        given:
        queue = (Queue<Integer>) constructor()

        when:
        queue.insert(input)

        then:
        queue.dequeue()  == front
        queue.size()     == size - 1

        where:
        size || front || input
        5    || 5     || [5, 4, 3, 2, 1]
        2    || 1     || [1, 2]
    }

    @Unroll
    def "Test dequeue() method on empty queue"() {
        given:
        queue = (Queue<Integer>) constructor()

        when:
        queue.dequeue()

        then:
        thrown DataStructure.EmptyDataStructureException
    }

    @Unroll
    def "Test peek() method on non-empty queue"() {
        given:
        queue = (Queue<Integer>) constructor()

        when:
        queue.insert(input)

        then:
        queue.peek()  == peek
        queue.size() == size

        where:
        size || peek || input
        5    || 5    || [5, 4, 3, 2, 1]
        2    || 1    || [1, 2]
    }

    @Unroll
    def "Test peek() method on empty queue"() {
        given:
        queue = (Queue<Integer>) constructor()

        when:
        queue.peek()

        then:
        thrown DataStructure.EmptyDataStructureException

    }

    @Unroll
    def "Create, then empty out entire queue"() {
        given:
        queue = (Queue<Integer>) constructor()

        when:
        queue.insert(input)

        for (int i = input.size() - 1; i >= 0; i--) {
            queue.dequeue()
        }

        then:
        queue.empty()
        queue.toString() == "[]"

        where:
        input        | _
        [1, 2, 3, 4] | _
        [1]          | _
        []           | _
    }

    @Unroll
    def "Convert queue to string via toString() method"() {
        given:
        queue = (Queue<Integer>) constructor()

        when:
        queue.insert(input)

        then:
        queue.toString() == string

        where:
        input           | string
        [1, 2, 3, 4, 5] | "[1, 2, 3, 4, 5]"
        [1, 2]          | "[1, 2]"
        [1]             | "[1]"
        []              | "[]"
    }
}

class QueueSpec_ArrayQueue<T> extends QueueSpec {

    def setup() {
        myClass = ArrayQueue
    }
}

class QueueSpec_LinkedQueue<T> extends QueueSpec {

    def setup() {
        myClass = LinkedQueue
    }
}

