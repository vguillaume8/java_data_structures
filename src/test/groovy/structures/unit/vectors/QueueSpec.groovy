package structures.unit.vectors

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import structures.commons.DataStructure
import structures.vectors.Queue
import structures.vectors.ArrayQueue
import structures.vectors.LinkedQueue

abstract class QueueSpec<T> extends Specification {

    @Shared Queue<Object> queue;

    abstract Queue<Object> constructor()
    abstract Queue<Object> constructor(Object[] input)
    abstract Queue<Object> constructor(Collection<Object> input)
    abstract Class         type()

    @Unroll
    def "Construct an empty queue from default constructor"() {

        expect:
        queue.empty()
        queue.size()     == 0
        queue.toString() == "[]"
    }

    @Unroll
    def "Construct a queue from an array of values"() {
        when:
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
        when:
        queue.dequeue()

        then:
        thrown DataStructure.EmptyDataStructureException
    }

    @Unroll
    def "Test peek() method on non-empty queue"() {
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
        when:
        queue.peek()

        then:
        thrown DataStructure.EmptyDataStructureException

    }

    @Unroll
    def "Create, then empty out entire queue"() {
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

class QueueSpec_queue<T> extends QueueSpec {

    def setup() {
        queue = new ArrayQueue<>()
    }

    @Override
    Queue<Object> constructor() {
        return new ArrayQueue<Object>()
    }

    @Override
    Queue<Object> constructor(Object[] input) {
        return new ArrayQueue<Object>(input)
    }

    @Override
    Queue<Object> constructor(Collection input) {
        return new ArrayQueue<Object>(input)
    }

    @Override
    Class type() {
        return ArrayQueue
    }
}

class QueueSpec_LinkedQueue<T> extends QueueSpec {
    def setup() {
        queue = new LinkedQueue<>()
    }

    @Override
    Queue<Object> constructor() {
        return new LinkedQueue<Object>()
    }

    @Override
    Queue<Object> constructor(Object[] input) {
        return new LinkedQueue<Object>(input)
    }

    @Override
    Class type() {
        return LinkedQueue
    }

    @Override
    Queue<Object> constructor(Collection input) {
        return new LinkedQueue<Object>(input)
    }
}

