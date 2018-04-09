package structures.unit.vectors

import spock.lang.Unroll
import spock.lang.Specification
import structures.commons.DataStructure.EmptyDataStructureException
import structures.vectors.LinkedList

class LinkedListSpec extends Specification {

  @Unroll
  def "#Check LinkedList equality"() {
    setup:
    LinkedList<Integer> list1
    LinkedList<Integer> list2

    when:
    list1 = new LinkedList<Integer>(input1)
    list2 = new LinkedList<Integer>(input2)

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
  def "#Construct an empty list"() {
    setup:
    LinkedList list = new LinkedList()

    expect:
    list.size() == 0
    list.empty()
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#Construct a non-empty list from array"() {
    setup:
    LinkedList list = new LinkedList(values)

    expect:
    list.size() == length
    list.empty() == empty

    where:
    values                 | length | empty
    [1, 2, 3] as Integer[] | 3      | false
    [1] as Integer[]       | 1      | false
    [] as Integer[]        | 0      | true
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#Construct a non-empty list by length"() {
    setup:
    LinkedList list = new LinkedList(values);

    expect:
    list.size() == length
    list.empty() == empty

    where:
    length | empty | values
    3      | false | [1, 2, 3] as Integer[]
    1      | false | ['a'] as Character[]
    0      | true  | [] as Double[]
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#contains()"() {
    setup:
    LinkedList list = new LinkedList(values)

    expect:
    list.contains(1) == contains

    where:
    values                 | contains
    [1, 2, 3] as Integer[] | true
    [2, 2, 3] as Integer[] | false
    [1] as Integer[]       | true
    [] as Integer[]        | false
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#get(index) with good data"() {
    setup:
    LinkedList list = new LinkedList(values)

    when:
    Object x = list.get(index)

    then:
    x == value

    where:
    values                 | index | value
    [1, 2, 3] as Integer[] | 0     | 1
    [1] as Integer[]       | 0     | 1
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#get(index) with bad data"() {
    setup:
    LinkedList list = new LinkedList(values)

    when:
    Object x = list.get(index)

    then:
    thrown IndexOutOfBoundsException

    where:
    values                 | index
    [1, 2, 3] as Integer[] | 4
    [] as Integer[]        | 0
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#insert()"() {
    setup:
    LinkedList list = new LinkedList()

    when:
    list.insert(value)

    then:
    list.contains(check) == contains

    where:
    value | check | contains
    1     | 1     | true
    1     | 2     | false
    "1"   | 1     | false
    "1"   | "1"   | true
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#insertFirst()"() {
    setup:
    LinkedList list = new LinkedList(values);

    when:
    list.prepend(value)

    then:
    list.get(0) == value

    where:
    values                 | value
    [1, 2, 3] as Integer[] | 4
    [1, 2, 3] as Integer[] | "1"
    [1, 2, 3] as Integer[] | null
    [] as Integer[]        | 1
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#insertLast()"() {
    setup:
    LinkedList list = new LinkedList(values);

    when:
    list.append(value)

    then:
    list.get(list.size()-1) == value

    where:
    values                 | value
    [1, 2, 3] as Integer[] | 4
    [1, 2, 3] as Integer[] | "1"
    [1, 2, 3] as Integer[] | null
    [] as Integer[]        | 1
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#insert(value, index)"() {
    setup:
    LinkedList list = new LinkedList(values);

    when:
    list.insert(value, index)

    then:
    list.get(index) == value

    where:
    values                 | value | index
    [1, 2, 3] as Integer[] | 4     | 2
    [1, 2, 3] as Integer[] | "1"   | 2
    [1, 2, 3] as Integer[] | null  | 0
    [] as Integer[]        | 1     | 0
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#empty() when initializing with an array"() {
    setup:
    LinkedList list = new LinkedList(values);

    expect:
    list.empty() == empty

    where:
    values                 | empty
    [1, 2, 3] as Integer[] | false
    [] as Integer[]        | true
  }


//------------------------------------------------------------------------------

  @Unroll
  def "#empty() when using basic constructor"() {
    setup:
    LinkedList list = new LinkedList();

    expect:
    list.empty() == empty

    where:
    empty = true
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#remove(index) with valid index"() {
    setup:
    LinkedList list = new LinkedList(values)

    when:
    def r = list.remove(index)

    then:
    list.contains(check) == false
    list.size() == length - 1
    r == removed

    where:
    values                 | index | check  | length | removed
    [1, 2, 3] as Integer[] | 0     | 1      | 3      | 1
    [1, 2, 3] as Integer[] | 1     | 2      | 3      | 2
    [1] as Integer[]       | 0     | 1      | 1      | 1
  }

  @Unroll
  def "#remove(index) with invalid input"() {
    setup:
    LinkedList list = new LinkedList(values)

    when:
    list.remove(index)

    then:
    thrown exception

    where:
    values                 | index | exception
    [1, 2, 3] as Integer[] | 4     | IndexOutOfBoundsException
    [] as Integer[]        | 0     | EmptyDataStructureException
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#remove()"() {
    setup:
    LinkedList list = new LinkedList(values);

    when:
    while (removals > 0) {
      list.remove()
      removals--;
    }

    then:

    if (list.size() > 0) {
      assert list.get(list.size()-1) == value

    } else {
      assert list.empty() == true
    }

    where:
    values                 | value | removals
    [1, 2, 3] as Integer[] | 1     | 2
    [1, 2, 3] as Integer[] | 2     | 1
    [1, 2]    as Integer[] | null  | 2
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#removeFirst()"() {
    setup:
    LinkedList list = new LinkedList(values);

    when:
    while (removals > 0) {
      list.removeFirst()
      removals--;
    }

    then:

    if (list.size() > 0) {
      assert list.get(0) == value

    } else {
      assert list.empty() == true
    }

    where:
    values                 | value | removals
    [1, 2, 3] as Integer[] | 3     | 2
    [1, 2, 3] as Integer[] | 2     | 1
    [1, 2]    as Integer[] | null  | 2
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#removeLast()"() {
    setup:
    LinkedList list = new LinkedList(values);

    when:
    while (removals > 0) {
      list.removeLast()
      removals--;
    }

    then:

    if (list.size() > 0) {
      assert list.get(list.size() - 1) == value

    } else {
      assert list.empty() == true
    }

    where:
    values                 | value | removals
    [1, 2, 3] as Integer[] | 1     | 2
    [1, 2, 3] as Integer[] | 2     | 1
    [1, 2]    as Integer[] | null  | 2
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#toString()" () {
    setup:
    LinkedList list = new LinkedList(values)

    when:
    String s = list.toString()

    then:
    s == string

    where:
    values                      | string
    [1, 2, 3] as Integer[]      | "[1, 2, 3]"
    ["1", "2", "3"] as String[] | "[1, 2, 3]"
    [] as Object[]              | "[]"
  }
}
