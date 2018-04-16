package structures.functional.vectors

import structures.vectors.LinkedStack
import spock.lang.Specification
import spock.lang.Unroll

class LinkedStackSpec extends Specification {

    /**
     * https://www.geeksforgeeks.org/the-stock-span-problem/
     * @return
     */
    @Unroll
    def "Stock span problem"() {
        setup:
        LinkedStack<Integer> stack
        List<Integer>  spans
        int            length

        when:
        length = stocks.size()
        stack  = new LinkedStack<Integer>()
        spans  = new ArrayList<Integer>(length)

        stack.push(0)
        spans[0] = 1

        for (int i = 1; i < length; i++) {

            while (!stack.empty() && stocks[stack.top()] <= stocks[i]) {
                stack.pop()
            }

            if (stack.empty()) {
                spans[i] = i + 1
            } else {
                spans[i] = i - stack.top()
            }

            stack.push(i)
        }

        then:
        spans == result

        where:
        stocks                        || result
        [20,  30, 10, 50, 70, 10, 20] || [1, 2, 1, 4, 5, 1, 2]
        [7,   6,  5,  4,  3,  2,  1]  || [1, 1, 1, 1, 1, 1, 1]
        [100, 80, 60, 70, 60, 75, 85] || [1, 1, 1, 2, 1, 4, 6]
        [1,   2,  3,  4,  5,  6,  7]  || [1, 2, 3, 4, 5, 6, 7]
        [20, 20]                      || [1, 2]
        [1]                           || [1]
    }

    /**
     * https://www.geeksforgeeks.org/stack-set-4-evaluation-postfix-expression/
     * @return
     */
    @Unroll
    def "Postfix expression evaluation"() {
        setup:
        LinkedStack<String> stack
        String c
        int length
        int result
        int operand1
        int operand2

        when:

        stack = new LinkedStack<String>()
        length = expression.length()

        // Only supports single digits 1-5
        // This is not a comprehensive evaluator,
        // it is just to prove the stack
        // is behaving as expected with small values
        for (int i = 0; i < length; i++) {
            c = expression.charAt(i)

            // If operand
            if (c == "1" ||
                c == "2" ||
                c == "3" ||
                c == "4" ||
                c == "5"
            ) {
                stack.push(c)

            // If operator
            } else if ( c == "+" ||
                        c == "-" ||
                        c == "*" ||
                        c == "/"
            ) {

                // Get last two operands
                operand1 = Integer.parseInt(stack.pop())
                operand2 = Integer.parseInt(stack.pop())

                // Operate
                switch (c) {
                    case "+":
                        result = operand1 + operand2
                        break
                    case "-":
                        result = operand2 - operand1
                        break
                    case "*":
                        result = operand1 * operand2
                        break
                    case "/":
                        result = (int) operand2 / operand1
                        break
                }

                stack.push(Integer.toString(result))
            }
        }

        result = Integer.parseInt(stack.top())

        then:
        result == answer

        where:
        answer || expression
        30     || "2 2 2 2 + + 4 * 2 3 * +"
         5     || "2 2 * 1 +"
        12     || "1 1 + 1 1 + + 2 + 2 *"
        10     || "5 5 +"
         7     || "3 4 * 5 - "
         2     || "4 2 /"

    }

    /**
     * https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
     * @return
     */
    @Unroll
    def "Parenthesis matching problem"() {
        setup:
        LinkedStack<String> stack = new LinkedStack<String>()
        int length = string.length()
        int i = 0
        String c
        boolean isBalanced = false

        when:
        while (i < length) {
            c = string.charAt(i)

            if (c == '(') {

                stack.push()

            } else if(c == ')') {

                try {
                    stack.pop()
                } catch (Exception e) {
                    break
                }

            }

            i++
        }

        if (i == length && stack.empty()) {
            isBalanced = true
        }

        then:
        isBalanced == balanced

        where:
        balanced || string
        true     || "()"
        true     || ""
        true     || "(())"
        true     || "()()()"
        true     || "(())()"
        false    || "(("
        false    || "(()))"
        false    || '))))'
        true     || '(((((())))))()()()()'
    }
}
