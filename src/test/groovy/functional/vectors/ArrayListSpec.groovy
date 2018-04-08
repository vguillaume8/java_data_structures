package functional.vectors

import spock.lang.Specification
import spock.lang.Unroll

class ArrayListSpec extends Specification{

    @Unroll
    def "Merge two sorted lists"() {

        // TODO - Implement
    }

    /**
     * http://www.techiedelight.com/0-1-knapsack-problem/
     */
    @Unroll
    def "0-1 Knapsack problem"() {

        // TODO - Implement
    }

    @Unroll
    def "Vector addition, subtraction, and dot product"() {
        setup:
        ArrayList<Integer> v1
        ArrayList<Integer> v2
        ArrayList<Integer> vsum
        ArrayList<Integer> vdif
        int vprod
        int op1
        int op2
        int len

        when:
        v1    = new ArrayList<Integer>()
        v2    = new ArrayList<Integer>()
        vsum  = new ArrayList<Integer>()
        vdif  = new ArrayList<Integer>()
        len   = sum.size()
        vprod = 0

        for (int i = 0; i < len; i++) {
            v1.add(input1[i])
            v2.add(input2[i])
        }

        for (int i = 0; i < len; i++) {
            op1 = v1.get(i)
            op2 = v2.get(i)

            vsum.add(op1 + op2) // v1 + v2
            vdif.add(op2 - op1) // v2 - v1
            vprod += op1 * op2  // v1 * v2
        }

        then:
        vprod           == product
        vsum.toString() == sum.toString()
        vdif.toString() == difference.toString()

        where:
        product || sum                || difference      || input1          || input2
        130     || [7, 9, 11, 13, 15] || [5, 5, 5, 5, 5] || [1, 2, 3, 4, 5] || [6, 7, 8, 9, 10]
        1       || [2]                || [0]             || [1]             || [1]
    }
}
