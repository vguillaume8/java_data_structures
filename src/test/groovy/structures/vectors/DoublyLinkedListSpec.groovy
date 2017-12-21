package structures.vectors

import spock.lang.Unroll
import spock.lang.Specification
import structures.vectors.DoublyLinkedList

class DoublyLinkedListSpec extends Specification {

  @Unroll
  def "#Construct an empty list"() {
    setup:
    DoublyLinkedList list = new DoublyLinkedList()

    expect:
    list.length() == 0
    list.empty() == true
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#Constructing a list with bad values"() {
    setup:
    DoublyLinkedList list

    when:
    if (value == null) {
      list = new DoublyLinkedList(value);
    } else {
      list = new DoublyLinkedList(value, "x")
    }

    then:
    thrown exception

    where:
    value | exception
    null  | NullPointerException
    -1    | IndexOutOfBoundsException

  }

//------------------------------------------------------------------------------


  @Unroll
  def "#Construct a non-empty list from array"() {
    setup:
    DoublyLinkedList list = new DoublyLinkedList(values)

    expect:
    list.length() == length
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
    DoublyLinkedList list = new DoublyLinkedList(length, "x")

    expect:
    list.length() == length
    list.empty() == empty

    where:
    length | empty
    3      | false
    1      | false
    0      | true
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#contains()"() {
    setup:
    DoublyLinkedList list = new DoublyLinkedList(values)

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
    DoublyLinkedList list = new DoublyLinkedList(values)

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
    DoublyLinkedList list = new DoublyLinkedList(values)

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
    DoublyLinkedList list = new DoublyLinkedList()

    when:
    list.insert(value)

    then:
    list.contains(check) == contains

    where:
    value | check | contains
    1     | 1     | true
    1     | 2     | false
    null  | 2     | false
    null  | null  | true
    "1"   | 1     | false
    "1"   | "1"   | true
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#insertFirst()"() {
    setup:
    DoublyLinkedList list = new DoublyLinkedList(values);

    when:
    list.insertFirst(value)

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
    DoublyLinkedList list = new DoublyLinkedList(values);

    when:
    list.insertLast(value)

    then:
    list.get(list.length()-1) == value

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
    DoublyLinkedList list = new DoublyLinkedList(values);

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
    DoublyLinkedList list = new DoublyLinkedList(values);

    expect:
    list.empty() == empty

    where:
    values                 | empty
    [1, 2, 3] as Integer[] | false
    [] as Integer[]        | true
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#empty() when initializing with specified length"() {
    setup:
    DoublyLinkedList list = new DoublyLinkedList(length, "x");

    expect:
    list.empty() == empty

    where:
    length | empty
    1      | false
    10     | false
    0      | true
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#empty() when using basic constructor"() {
    setup:
    DoublyLinkedList list = new DoublyLinkedList();

    expect:
    list.empty() == empty

    where:
    empty = true
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#remove(index) with valid index"() {
    setup:
    DoublyLinkedList list = new DoublyLinkedList(values)

    when:
    def r = list.remove(index)

    then:
    list.contains(check) == false
    list.length() == length - 1
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
    DoublyLinkedList list = new DoublyLinkedList(values)

    when:
    list.remove(index)

    then:
    thrown IndexOutOfBoundsException

    where:
    values                 | index
    [1, 2, 3] as Integer[] | 4
    [] as Integer[]        | 0
  }

//------------------------------------------------------------------------------

  @Unroll
  def "#remove()"() {
    setup:
    DoublyLinkedList list = new DoublyLinkedList(values);

    when:
    while (removals > 0) {
      list.remove()
      removals--;
    }

    then:

    if (list.length() > 0) {
      assert list.get(list.length()-1) == value

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
    DoublyLinkedList list = new DoublyLinkedList(values);

    when:
    while (removals > 0) {
      list.removeFirst()
      removals--;
    }

    then:

    if (list.length() > 0) {
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
    DoublyLinkedList list = new DoublyLinkedList(values);

    when:
    while (removals > 0) {
      list.removeLast()
      removals--;
    }

    then:

    if (list.length() > 0) {
      assert list.get(list.length() - 1) == value

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
    DoublyLinkedList list = new DoublyLinkedList(values)

    when:
    String s = list.toString()

    then:
    s == string

    where:
    values                      | string
    [1, 2, 3] as Integer[]      | "1, 2, 3"
    ["1", "2", "3"] as String[] | "1, 2, 3"
    [] as Object[]              | ""
  }
}
