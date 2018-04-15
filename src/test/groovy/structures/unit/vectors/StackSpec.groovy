package structures.unit.vectors

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import structures.commons.DataStructure
import structures.vectors.Stack
import structures.vectors.ArrayStack
import structures.vectors.LinkedStack

abstract class StackSpec<T> extends Specification {

    @Shared Stack<Object> stack;

    abstract Stack<Object> constructor();
    abstract Stack<Object> constructor(Object[] input)
    abstract Stack<Object> constructor(Collection<Object> input)

    @Unroll
    def "Construct an empty stack from default constructor"() {

        expect:
        stack.empty()
        stack.size()     == 0
        stack.toString() == "[]"
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
}

class StackSpeck_ArrayStack<T> extends StackSpec {
    def setup() {
        stack = new ArrayStack<>()
    }

    @Override
    Stack<Object> constructor() {
        return new ArrayStack<Object>()
    }

    @Override
    Stack<Object> constructor(Object[] input) {
        return new ArrayStack<Object>(input)
    }

    @Override
    Stack<Object> constructor(Collection input) {
        return new ArrayStack<Object>(input)
    }
}

class StackSpec_LinkedStack<T> extends StackSpec {
    def setup() {
        stack = new LinkedStack<>()
    }

    @Override
    Stack<Object> constructor() {
        return new LinkedStack<Object>()
    }

    @Override
    Stack<Object> constructor(Object[] input) {
        return new LinkedStack<Object>(input)
    }

    @Override
    Stack<Object> constructor(Collection input) {
        return new LinkedStack<Object>(input)
    }
}

