package structures.functional.vectors

import structures.vectors.ArrayList
import spock.lang.Specification
import spock.lang.Unroll

class ArrayListSpec extends Specification{

    @Unroll
    def "Rotate matrix 90 degrees clockwise"() {
        setup:
        ArrayList< ArrayList<Integer> > matrix
        int n
        String temp
        String before
        String after
        String m1 = "[" +
                        "[1, 2, 3, 4, 5], " +
                        "[1, 2, 3, 4, 5], " +
                        "[1, 2, 3, 4, 5], " +
                        "[1, 2, 3, 4, 5], " +
                        "[5, 4, 3, 2, 1]"   +
                    "]"

        String m2 = "[" +
                        "[5, 1, 1, 1, 1], " +
                        "[4, 2, 2, 2, 2], " +
                        "[3, 3, 3, 3, 3], " +
                        "[2, 4, 4, 4, 4], " +
                        "[1, 5, 5, 5, 5]"   +
                    "]"

        when:
        matrix = new ArrayList< ArrayList<Integer> >()
        n = 5

        for (int i = 0; i < n; i++) {

            matrix.insert(new ArrayList<Integer>())

            for (int j = 0; j < n; j++) {

                if (i == n-1){
                    matrix.get(i).insert(i - j + 1)
                } else {
                    matrix.get(i).insert(j + 1)
                }

            }
        }

        before = matrix.toString()

        // Transpose matrix while
        // simultaneously inverting rows
        for (int i = 0; i < n; i++) {

            // Transpose
            for (int j = i; j < n; j++) {
                temp = matrix.get(i).get(j)
                matrix.get(i).update(matrix.get(j).get(i), j)
                matrix.get(j).update(temp, i)
            }

            // Invert row
            for (int j = 0; j < n / 2; j++) {
                temp = matrix.get(i).get(j)
                matrix.get(i).update(matrix.get(i).get(n-1-j), j)
                matrix.get(i).update(temp, n-1-j)
            }
        }

        after = matrix.toString()

        then:
        before == m1
        after  == m2
    }

    @Unroll
    def "Selection sort"() {
        setup:
        ArrayList<Integer> list
        int len
        int min
        int temp

        when:
        list = new ArrayList<Integer>()

        for (Integer i : input) {
            list.insert(i)
        }

        len = list.size()

        for (int i = 0; i < len; i++) {
            min = i

            for (int j = i; j < len; j++) {
                if (list.get(j) < list.get(min)) {
                    min = j
                }
            }

            if (min != i) {
                temp = list.get(i)
                list.update(list.get(min), i)
                list.update(temp, min)
            }
        }

        then:
        list.toString() == string

        where:
        string               || input
        "[1, 2, 3, 4, 5, 6]" || [2, 1, 3, 5, 6, 4]
        "[1, 2]"             || [2, 1]
        "[1, 2, 3]"          || [1, 2, 3]
        "[1]"                || [1]
        "[]"                 || []
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
            v1.insert(input1[i])
            v2.insert(input2[i])
        }

        for (int i = 0; i < len; i++) {
            op1 = v1.get(i)
            op2 = v2.get(i)

            vsum.insert(op1 + op2) // v1 + v2
            vdif.insert(op2 - op1) // v2 - v1
            vprod += op1 * op2     // v1 * v2
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
