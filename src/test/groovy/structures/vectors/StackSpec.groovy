package structures.vectors

import spock.lang.Unroll
import spock.lang.Specification
import structures.vectors.Stack

class StackSpec extends Specification {

    @Unroll
    def "#Construct an empty Stack"() {
        setup:
        Stack stack = new Stack()

        expect:
        stack.size() == 1
        stack.empty() == true
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#Construct stack from array"() {
        setup:
        Stack stack = new Stack(values);

        expect:
        stack.size() == size
        stack.empty() == empty

        where:
        values                 | size | empty
        [1, 2, 3] as Integer[] | 3    | false
        [1, 2] as Integer[]    | 2    | false
        [1] as Integer[]       | 1    | false
        [] as Integer[]        | 0    | true

    }

//------------------------------------------------------------------------------

    @Unroll
    def "#push()"() {
        setup:
        Stack stack = new Stack();

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
        Stack stack = new Stack(values);

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
        Stack stack = new Stack();

        when:
        stack.pop()

        then:
        thrown EmptyStackException
    }

//------------------------------------------------------------------------------

    @Unroll def "#top()"() {
        setup:
        Stack stack = new Stack(values);

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
        Stack stack = new Stack();

        when:
        stack.top()

        then:
        thrown EmptyStackException
    }

//------------------------------------------------------------------------------

    @Unroll
    def "#toString()"() {
        setup:
        Stack stack = new Stack(values);

        expect:
        stack.toString() == string

        where:
        values                  | string
        [1, 2, 3] as Integer[]  | "3, 2, 1"
        [1, 2] as Integer[]     | "2, 1"
        [1] as Integer[]        | "1"
        [] as Integer[]         | ""
    }
}
