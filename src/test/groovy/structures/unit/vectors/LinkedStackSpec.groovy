package structures.unit.vectors

import spock.lang.Ignore
import spock.lang.Unroll
import spock.lang.Specification
import structures.commons.DataStructure.EmptyDataStructureException
import structures.vectors.LinkedStack;

/**
 * @depracated
 */
@Ignore
class LinkedStackSpec extends Specification {

    @Unroll
    def "Construct Stack from Java Collection"() {
        when:
        LinkedStack<Integer> stack = new LinkedStack<Integer>(input)

        then:
        stack.size()     == input.size()
        stack.toString() == string

        where:
        input        | string
        [1, 2, 3, 4] | "[4, 3, 2, 1]"
        [1, 2]       | "[2, 1]"
        [1]          | "[1]"
        []           | "[]"
    }

    @Unroll
    def "#Check Stack equality"() {
        setup:
        LinkedStack<Integer> list1
        LinkedStack<Integer> list2

        when:
        list1 = new LinkedStack<Integer>(input1)
        list2 = new LinkedStack<Integer>(input2)

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
    def "#Construct an empty Stack"() {
        setup:
        LinkedStack stack = new LinkedStack()

        expect:
        stack.size() == 0
        stack.empty()
    }

    @Unroll
    def "#Construct an empty Stack from array"() {
        setup:
        LinkedStack stack = new LinkedStack(values)

        expect:
        stack.size() == size
        stack.empty() == isEmpty

        where:
        values                 | size | isEmpty
        [] as Integer[]        | 0    | true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#Construct stack from non-empty array"() {
        when:
        LinkedStack<Integer> stack = new LinkedStack<Integer>(values);

        then:
        stack.empty() == isEmpty
        stack.size() == size

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
        LinkedStack stack = new LinkedStack();

        when:
        int i = 0
        while (pushes > 0) {
            stack.push(values[i])
            pushes--
            i++
        }

        then:
        stack.top() == top

        where:
        values                  | pushes | top
        [1, 2, 3] as Integer[]  | 3      | 3
        [1, 2] as Integer[]     | 2      | 2
        [1] as Integer[]        | 1      | 1
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#pop()"() {
        setup:
        LinkedStack stack = new LinkedStack(values);

        when:
        int i = 0
        while (pops > 0) {
            stack.pop()
            pops--
            i++
        }

        then:
        stack.top() == top

        where:
        values                  | pops | top
        [1, 2, 3] as Integer[]  | 2    | 1
        [1, 2] as Integer[]     | 1    | 1
        [1] as Integer[]        | 0    | 1
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#pop() an empty Stack"() {
        setup:
        LinkedStack stack = new LinkedStack();

        when:
        stack.pop()

        then:
        thrown EmptyDataStructureException
    }

//------------------------------------------------------------------------------

    @Unroll def "#top()"() {
        setup:
        LinkedStack stack = new LinkedStack(values);

        expect:
        stack.top() == top

        where:
        values                  | top
        [1, 2, 3] as Integer[]  | 3
        [1, 2] as Integer[]     | 2
        [1] as Integer[]        | 1
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#top() an empty Stack"() {
        setup:
        LinkedStack stack = new LinkedStack();

        when:
        stack.top()

        then:
        thrown EmptyDataStructureException
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#toString()"() {
        setup:
        LinkedStack stack = new LinkedStack(values);

        expect:
        stack.toString() == string

        where:
        values                  | string
        [1, 2, 3] as Integer[]  | "[3, 2, 1]"
        [1, 2] as Integer[]     | "[2, 1]"
        [1] as Integer[]        | "[1]"
        [] as Integer[]         | "[]"
    }
}
