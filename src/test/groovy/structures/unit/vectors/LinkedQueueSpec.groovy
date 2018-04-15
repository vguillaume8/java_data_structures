package structures.unit.vectors

import spock.lang.Ignore
import spock.lang.Unroll
import spock.lang.Specification
import structures.commons.DataStructure.EmptyDataStructureException
import structures.vectors.LinkedQueue;

@Ignore
class LinkedQueueSpec extends Specification {

    @Unroll
    def "Construct Queue from Java Collection"() {
        when:
        LinkedQueue<Integer> list = new LinkedQueue<Integer>(input)

        then:
        list.size()     == input.size()
        list.toString() == input.toString()

        where:
        input        | _
        [1, 2, 3, 4] | _
        [1, 2]       | _
        [1]          | _
        []           | _
    }

    @Unroll
    def "#Check Queue equality"() {
        setup:
        LinkedQueue<Integer> list1
        LinkedQueue<Integer> list2

        when:
        list1 = new LinkedQueue<Integer>(input1)
        list2 = new LinkedQueue<Integer>(input2)

        then:
        list1.equals(list2) == equals

        where:
        equals || input1       || input2
        false  || [1, 1, 1, 1] || [1, 2, 3, 4]
        false  || [1, 2, 3, 4] || [4, 3, 2, 1]
        true   || [1, 2, 3, 4] || [1, 2, 3, 4]
        false  || [1, 2, 3, 4] || [1, 2, 3]
        true   || []           || []
        false  || []           || [1]
        true   || [1]          || [1]
    }

    @Unroll
    def "#Construct an empty Queue"() {
        setup:
        LinkedQueue queue = new LinkedQueue()

        expect:
        queue.size() == 0
        queue.empty()
    }

    @Unroll
    def "#Construct an empty Queue from array"() {
        setup:
        LinkedQueue queue = new LinkedQueue(values)

        expect:
        queue.size() == size
        queue.empty() == isEmpty

        where:
        values                 | size | isEmpty
        [] as Integer[]        | 0    | true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#Construct queue from non-empty array"() {
        when:
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>(values);

        then:
        queue.empty() == isEmpty
        queue.size() == size

//        queue.size() == size
//        queue.empty() == isEmpty

        where:
        values                 | size | isEmpty
        [1, 2, 3] as Integer[] | 3    | false
        [1, 2] as Integer[]    | 2    | false
        [1] as Integer[]       | 1    | false
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#push()"() {
        setup:
        LinkedQueue queue = new LinkedQueue();

        when:
        int i = 0
        while (pushes > 0) {
            queue.insert(values[i])
            pushes--
            i++
        }

        then:
        queue.peek() == peek

        where:
        values                  | pushes | peek
        [1, 2, 3] as Integer[]  | 3      | 1
        [1, 2] as Integer[]     | 2      | 1
        [1] as Integer[]        | 1      | 1
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#pop()"() {
        setup:
        LinkedQueue queue = new LinkedQueue(values);

        when:
        int i = 0
        while (pops > 0) {
            queue.remove()
            pops--
            i++
        }

        then:
        queue.peek() == peek

        where:
        values                  | pops | peek
        [1, 2, 3] as Integer[]  | 2    | 3
        [1, 2] as Integer[]     | 1    | 2
        [1] as Integer[]        | 0    | 1
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#pop() an empty Queue"() {
        setup:
        LinkedQueue queue = new LinkedQueue();

        when:
        queue.remove()

        then:
        thrown EmptyDataStructureException
    }

//------------------------------------------------------------------------------

    @Unroll def "#peek()"() {
        setup:
        LinkedQueue queue = new LinkedQueue(values);

        expect:
        queue.peek() == peek

        where:
        values                  | peek
        [1, 2, 3] as Integer[]  | 1
        [1, 2] as Integer[]     | 1
        [1] as Integer[]        | 1
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#peek() an empty Queue"() {
        setup:
        LinkedQueue queue = new LinkedQueue();

        when:
        queue.peek()

        then:
        thrown EmptyDataStructureException
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#toString()"() {
        setup:
        LinkedQueue queue = new LinkedQueue(values);

        expect:
        queue.toString() == string

        where:
        values                  | string
        [1, 2, 3] as Integer[]  | "[1, 2, 3]"
        [1, 2] as Integer[]     | "[1, 2]"
        [1] as Integer[]        | "[1]"
        [] as Integer[]         | "[]"
    }
}
