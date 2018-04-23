package structures.unit.vectors

import spock.lang.Shared
import spock.lang.Unroll
import structures.commons.DataStructure
import structures.vectors.Stack
import structures.vectors.ArrayStack
import structures.vectors.LinkedStack
import test.Spec

abstract class StackSpec<T> extends Spec {

    @Shared Stack<Object> stack;

    @Unroll
    def "Construct an empty stack from default constructor"() {

        expect:
        stack.empty()
        stack.size()     == 0
        stack.toString() == "[]"
    }

    @Unroll
    def "Construct a stack from an array of values"() {
        when:
        stack.insert(input)
        Stack<Integer> s = constructor(input as Integer[])

        then:
        stack == s

        where:
        input        | _
        [1, 2, 3, 4] | _
        [1, 2]       | _
        [1]          | _
        []           | _

    }

    @Unroll
    def "Construct a stack from a Java collection of values"() {
        when:
        stack.insert(input)
        Stack<Integer> s = constructor(input)

        then:
        stack == s

        where:
        input        | _
        [1, 2, 3, 4] | _
        [1, 2]       | _
        [1]          | _
        []           | _

    }

    @Unroll
    def "Check equality between stacks by content"() {

        when:
        Stack<Object> first  = constructor(input1)
        Stack<Object> second = constructor(input2)

        then:
        first.equals(second) == equals

        where:
        input1          | input2          | equals
        [1, 2, 3, 4, 5] | [1, 2, 3, 4, 5] | true
        [1, 2, 3, 4, 5] | [1, 2, 3, 4]    | false
        []              | []              | true
    }

    @Unroll
    def "Test push() method"() {

        when:
        for (Integer i : input) {
            stack.push(i)
        }

        then:
        stack.empty()    == empty
        stack.size()     == size
        stack.toString() == string

        where:
        size | empty ||string             || input
        5    | false || "[1, 2, 3, 4, 5]" || [5, 4, 3, 2, 1]
        2    | false || "[2, 1]"          || [1, 2]
        0    | true  || "[]"              || []
    }

    @Unroll
    def "Test pop() method on non-empty stack"() {
        when:
        stack.insert(input)

        then:
        stack.pop()  == top
        stack.size() == size - 1

        where:
        size || top || input
        5    || 1   || [5, 4, 3, 2, 1]
        2    || 2   || [1, 2]
    }

    @Unroll
    def "Test pop() method on empty stack"() {
        when:
        stack.pop()

        then:
        thrown DataStructure.EmptyDataStructureException
    }

    @Unroll
    def "Test top() method on non-empty stack"() {
        when:
        stack.insert(input)

        then:
        stack.top()  == top
        stack.size() == size

        where:
        size || top || input
        5    || 1   || [5, 4, 3, 2, 1]
        2    || 2   || [1, 2]
    }

    @Unroll
    def "Test top() method on empty stack"() {
        when:
        stack.top()

        then:
        thrown DataStructure.EmptyDataStructureException

    }

    @Unroll
    def "Create, then empty out entire stack"() {
        when:
        stack.insert(input)

        for (int i = input.size() - 1; i >= 0; i--) {
            stack.pop()
        }

        then:
        stack.empty()
        stack.toString() == "[]"

        where:
        input        | _
        [1, 2, 3, 4] | _
        [1]          | _
        []           | _
    }

    @Unroll
    def "Convert stack to string via toString() method"() {
        when:
        stack.insert(input)

        then:
        stack.toString() == string

        where:
        input           | string
        [1, 2, 3, 4, 5] | "[5, 4, 3, 2, 1]"
        [1, 2]          | "[2, 1]"
        [1]             | "[1]"
        []              | "[]"
    }
}

class StackSpeck_ArrayStack<T> extends StackSpec {

    def setup() {
        myClass = ArrayStack
        stack = new ArrayStack<>()
    }
}

class StackSpec_LinkedStack<T> extends StackSpec {

    def setup() {
        myClass = LinkedStack
        stack = new LinkedStack<>()
    }
}

