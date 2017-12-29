package structures.vectors

import spock.lang.Unroll
import spock.lang.Specification
import structures.commons.DataStructure.EmptyDataStructureException
import structures.vectors.Queue;

class QueueSpec extends Specification {
    @Unroll
    def "#Construct an empty Queue"() {
        setup:
        Queue queue = new Queue()

        expect:
        queue.size() == 0
        queue.empty() == true
    }

    @Unroll
    def "#Construct an empty Queue from array"() {
        setup:
        Queue queue = new Queue(values)

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
        Queue<Integer> queue = new Queue<Integer>(values);

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
        Queue queue = new Queue();

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
        Queue queue = new Queue(values);

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
        Queue queue = new Queue();

        when:
        queue.remove()

        then:
        thrown EmptyDataStructureException
    }

//------------------------------------------------------------------------------

    @Unroll def "#peek()"() {
        setup:
        Queue queue = new Queue(values);

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
        Queue queue = new Queue();

        when:
        queue.peek()

        then:
        thrown EmptyDataStructureException
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#toString()"() {
        setup:
        Queue queue = new Queue(values);

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
