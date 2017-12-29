# ArrayList

An array list is a common data structure used in many programming languages.
In other programming languages, it may be called something else, but most
programming languages should have them. Array-lists are alternatives to arrays.

**Basic ArrayList Operations:**

| Operation             | Description                                                      | arguments    |
| :-------------------: | :--------------------------------------------------------------  | :----------: |
| [`search()`](#search) | Retrieves an element from a specified position in the array-list | index        |
| [`update()`](#update) | Overwrites an element at a specified position in the array-list  | index, value |
| [`insert()`](#insert) | Inserts an element into a specified position in the array-list   | index, value |
| [`remove()`](#remove) | Removes an element from a specified position in the array-list   | index        |

Arrays are statically sized. This means, the size of the array does not change.
This is why we must pass the length of an array as a parameter upon instantiating
it. Otherwise, the computer does not know how much space to allocate.

Code Snippet:

```java
class AllocateArray {
    int[] array0 = new int[10];  // Allocate an array with 10 slots
    
    int[] array1 = new int[];    // ERROR, this will not compile
}
```

Compiler Error:
```
Array initializer expected
```

Allocating an array before using it is great if we know much space out program
is going to use. But, often we do not know how much space we will need. 
This is where array-lists come in.

## What's an ArrayList?

Array-lists are dynamically sized data structures. They are lists that grow with
respect to the input. For example, if I need a list of `10` people, my array-list
will be of size `10`. If I would like to list `1,000` people, then my array-list
wil be of size `1,000`. In fact, for integer number of `n` inputs, where `n >= 0`, 
our array list will be of size `n`. 

Program:

```java
import java.util.ArrayList;
class FirstArrayList {
    
    public static void main(String[] args) {
        int n = 1000;
        
        // Initialize an ArrayList to have an initial capacity of 1000
        ArrayList<Integer> arrayList = new ArrayList<>(n);
        
        // Insert n elements
        for (int i = 0; i < n; i++) 
            arrayList.add(i);
        
        System.out.println(arrayList.size());   // 1000
        
        // Insert n more elements
        for (int i = 0; i < n; i++) 
            arrayList.add(i);                  
        
        System.out.println(arrayList.size());   // 2000
    }
}
```

Output:
```
1000
2000
```

## How do they work?

An ArrayList in essence is just an array, with a few rules. As mentioned an array
has a fixed allocated size, so how can the array-list be an array? Well, who says we can't 
just reallocate more space when our array fills up? That's what an array-list is. We have
an array, and insert values into it normally. But, when the array becomes full, we
make another array with more space, copy the old values into the new array, discard the old array,
then begin inserting new values into the new array.

**Definition:** *An array-list is a data structure that stores values into an array, where
if the array becomes full, a new copy of the array is made with extra space at the end
so that insertion can continue.*

### <a name="search"></a> Finding elements

Finding an element in an array-list is as simple as finding an element in an array.
Remember, after all, the array-list is just a wrapper around an array. We can access
the elements by index as follows:

Program:
```java
import java.util.ArrayList;
class GetAnElement {
    
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        
        arrayList.add("Jalia");
        arrayList.add("Jelani");
        arrayList.add("Jabari");
        
        String string = arrayList.get(1);
        
        System.out.println(string);
    }
}
```

Output:
```
Jelani
```

In the background, something along the lines of the following is happening:

Snippet:

```java
T[] elements;  

public T get(int index) {
    return elements[index];
}
```

Getting a value from a specified index does not require any additional function calls
data swaps, or loop iterations, or data comparisons. Therefore, its runtime is constant for all cases.

Time Complexity of `get()`:

| Case         |  Runtime | Measured in      |
| :----------: | :------: | :-------------:  |
| Best Case    | Θ(1)     | Data comparisons |
| Average Case | Θ(1)     | ''               |
| Worst Case   | Θ(1)     | ''               |

### <a name="update"></a> Updating a value at a given index

Retrieving an element was simple enough. Luckily, updating a value is
just as simple. Remember, after all, its an "array" list. The following
code describes the simplicity of updating a value in an array-list.

Snippet:

```java
T[] elements;

public void update(int index, T value) {
    elements[index] = value;
}
```

This operation does not require any extra work to be done, function calls, or
data swaps. Therefore this operation is completed in constant time.

Time Complexity of `update()`:

| Case         |  Runtime | Measured in                                  |
| :----------: | :------: | :------------------------------------------: |
| Best Case    | Θ(1)     | Method calls, data swaps, or loop iterations |
| Average Case | Θ(1)     | ''                                           |
| Worst Case   | Θ(1)     | ''                                           |

### <a name="insert"></a> (The essence of)  inserting elements into an ArrayList

Inserting an element is a little more difficult. First, we are going
to explore the general idea of inserting an element into an array-list,
and how it differs from any array. We will lay down some fundamental ideas,
and then explore in greater detail how insertion into an array-list
may be **implemented** when implementing an array-list data structure.

Assume, we have an array-list `names` 
that looks as follows:

```
names = ["Jabari", "Jelani", "Jalia"]
```

Let's say we wanted to insert an element `"Jamaal"` to into the array-list. Before we
can do that, we need to make space for it. Whether we want to insert it at the front,
the middle, or the back, we need to allocate a new spot.

#### Inserting an element at the end

Let's start with the back:

1. Make a new array of with at least 4 spaces
2. Copy the old values into the new array (in order)
3. Place the new value in the next vacant spot
4. Set the temporary list as our main list `names`

```
temp = ["", "", "", ""]

temp = ["Jabari", "Jelani", "Jalia", ""]

temp = ["Jabari", "Jelani", "Jalia", "Jamaal"]

names = temp
``` 

Easy enough, how about the front? 

#### Inserting an element at the front

Let's say we want to insert a string `Jenelle` as
the first element. How would we go about it?

1. If our array is full, make a new array with a large enough capacity to hold a new element
2. Place the new value at the front of the new array
3. Copy all the values from the old array into the new array with an offset of 1
from the front of the array. This means, the value that used to be in position `0`
goes to position `1` of the new array, `1` goes to `2`, and so on.
4. Set the temporary list as our main list `names`

```
temp = ["", "", "", "", ""]

temp = ["Jenelle", "", "", "", ""]

temp = ["Jenelle", "Jabari", "Jelani", "Jalia", "Jamaal"]

names = temp
```

Ok, now we will insert an element at some arbitrary position in the array-list.

#### Inserting an element somewhere in the middle

Our array-list `names` now looks as follows:

```
names = ["Jenelle", "Jabari", "Jelani", "Jalia", "Jamaal"]
```

We want to insert an element `"Jarrett"` at index 2, that is, between the values
`"Jabari"` and `"Jelani"`. How?

1. If our array is full, allocate a large enough array to hold the old values and the new one
2. Copy the old values from the old array to the new array
3. Shift all the values from the desired index up one position to make space for the new value
4. Place the new value in the desired position
5. Set the temporary list as our main list `names`

```
temp = ["", "", "", "", "", "", ""]

temp = ["Jenelle", "Jabari", "Jelani", "Jalia", "Jamaal", ""]

temp = ["Jenelle", "Jabari", "" ,"Jelani", "Jalia", "Jamaal"]

temp = ["Jenelle", "Jabari", "Jarrett" ,"Jelani", "Jalia", "Jamaal"]

names = temp
```

We now have a way to insert an element at any position in the array. However, we skipped
a few details. How are we shifting the values? And most importantly, how is this
added step impacting the performance of our insert operation?

### Shifting the array

The two main things that make array-lists possible, and different from
an array is the shifting of elements, and the resizing of the internal array.
Allocating a large enough array is simple enough, so we won't explore that
any more. What's interesting is the shifting. But, what is shifting?

Given an array (with labeled indices):

```
letters = ['a', 'b', 'c', 'd', 'e']
            0    1    2    3    4
```

Shifting the array to the right (by 1) would produce the following:

```
letters = ['', 'a', 'b', 'c', 'd'] 'e'
            0   1    2    3    4
```

We see that the `e` "fell off" the list. This is why we allocate space
first. But, we will get back to that later. For the time being, let's
figure out how this shifting takes place, and what are the "costs" of
this operation.

Program:

```java
import java.util.Arrays;
class ArrayShift {
    public static void main(String[] args) {
        char[] letters = {'a', 'b', 'c', 'd', 'e'};
        
        System.out.println(Arrays.toString(letters));    // Display array first
        letters = shiftRight(letters);                   // Shift right by one
        System.out.println(Arrays.toString(letters));    // Display it again
    }
    
    public static char[] shiftRight(char[] array) {
        int n = array.length;
    
        // Move everything up
        for (int i = n-1; i >= 1; i--) {
          array[i] = array[i-1];
        }
    
        // Overwrite value at first index (optional)
        // This is for illustration purposes
        array[0] = '?';
    
        return array;
      }
}
```

Output:

```
[a, b, c, d, e]
[?, a, b, c, d]
```

We now have an open space to insert a value in the front. In fact, this
is what happened just before we inserted an element at the 
front of the array-list. But, that was a lot of work. Notice how, for a 
list of length `n`, in order to insert an element at the front, we needed
to perform `n` data swaps. This means the runtime to shift an array is
`O(n)`, where runtime is measured in number of data swaps with respected to
to the elements in the array. Provided that this operation gets called once per insertion, the
worst case of inserting into an array-list degrades to `O(n)` as well.
What about deletion?

### <a name="remove"></a> Removing elements 

Removing an element from an array-list presents a similar problem.
Consider the following array-list:

```
letters = ['a', 'b', 'c', 'd', 'e']
            0    1    2    3    4
```

Let's say we want to remove the letter `b`. Just as we perform a shift right, we
can perform a shift left, this time effectively overwriting a value, rather than
making space for a new one. We will omit the java code, and just show the result.

```    
letters = ['a', 'c', 'c', 'd', 'e']     // swap c to position 1

letters = ['a', 'c', 'd', 'd', 'e']     // swap d to position 2
           
letters = ['a', 'c', 'd', 'e', '?']     // swap e to position 3
            0    1    2    3    4
```

That was pretty simple. Note though, for an array of length `n`, we still performed
roughly `n` data swaps. In this case, we performed `3` swaps. Had we wanted to remove
`c`, it would have been `2` swaps, `d` requires `1` swap. See a pattern? It turns out
that the amount of swaps necessary to remove an element can be expressed as a function `T(n)`
in terms of index `i` and length `n`:

```
T(n) = n - 1 - i
       5 - 1 - 1
       3
```

*The number of swaps to remove an element from the ith position in the array is equal
to the number of elements after the specified index that need to be shifted down.*

After all, the rest of the array must be shifted down one. Because `T(n) = n - i - 1`, we see that,
the worst case is when `i = 0`. This means, we want to remove a value from the front of
the array, and we need to shift the entire rest of the array down one.

***Note:** `remove()` in the worst case runs in linear time `O(n)`.*

What about the best case? Well, this means we want to minimize `T(n) = n - i - 1`. 
`T(n)`'s lower bound is `0` because we cannot have negative time. Thus, we set
`T(n) = 0`, then solve for `i`. *Note: * We are treating `i` as a variable for
algebraic purposes, but at runtime, `i` is a constant. This is why our time
function is `T(n)` rather than `T(n, i)`.

```
T(n) = n - i - 1
0 = n - i -1
i = n - 1
```

We see that for `i = n - 1`, we do not need to perform any swaps. But, what does this
case mean in words? Well, its when we want to remove the last value from the array.
This makes sense in that there are no values that follow it to shift into place - 
no addition work needs to be done.

***Note:** `remove()` in the best case runs in constant time `Ω(1)`.*

We now have upper and lower bounds for our runtime function `T(n)`. We will not do 
an in-depth analysis about the average case, but as `T(n)`  is a linear function,
and we have no data about the average index `i` that a user will remove from, we
drop the constant `i` and say `T(n) ≈ n`. Notice how `T(n)` is still *proportional*
to `n`, thus, the average case degrades to `O(n)`.

Time Complexity of `remove()`:

| Case         |  Runtime | Measured in |
| :----------: | :------: | :---------: |
| Best Case    | Ω(1)     | Data swaps  |
| Average Case | O(n)     | ''          |
| Worst Case   | O(n)     | ''          |

This is the process to remove an element. But, we left one thing a mystery. Note
the state of the array after the removal.

```
letters = ['a', 'c', 'd', 'e', '?']
            0    1    2    3    4
```

What happens to the value at index `4`? We just removed an element from the list of
size `5`, so it should now be of size `4`, right? That will be answered in the next
section.

## Dynamic memory allocation

This is the bread and butter of the array-list. It is the on the fly resizing
of the internal array that allows all of this to work. Consider the following array:

```
letters = ['a', 'c', 'd', 'e', '?']
            0    1    2    3    4
```

Previously, at least in java, we accessed the length of the array with the 
`letters.length`. However, with an array-list, we must keep track of the 
length ourselves. We create an instance variable `size`, that we increment
and decrement for each insertion and deletion respectively. So, now we 
have two things to keep track of.

Program:

```java
class ArrayList {
    int size;
    T[] elements;
    
    public void insert(int index, T value) {
        elements[index] = value;
        size++;
    }
    
    public T remove(int index) {
        size--;
        return elements[index];
    }
}
```

Now, let's visualize out array-list as follows:

```
letters = ['a', 'c', 'd', 'e', '?'], size = 4
            0    1    2    3    4
                           ^
```

*Where `^` denotes the end of array.*

Now, just like with arrays of size `n`, we do not care for indices
`i` such that `i < 0` or `i >= n`. So, now that we have removed a value
and `size = 4`, we don't care about the `?` that follows it. In fact, let's
perform two more removals:

```
letters = ['a', 'c', 'd', 'e', '?'], size = 4
            0    1    2    3    4
                           ^
                           
letters = ['a', 'c', '?', '?', '?'], size = 3
            0    1    2    3    4
                   ^       
                           
letters = ['a', 'c', '?', '?', '?'], size = 1
            0    1    2    3    4
                 ^                                                                     
``` 

## Performance

Time-Complexity:

| Operation             | Worst case | Average case | Best case | Measured in      |
| :-------------------: | :--------: | :----------: | :-------: | :--------------: |
| [`search()`](#search) | Θ(1)       | Θ(1)         | Θ(1)      | Data comparisons                          |
| [`update()`](#update) | Θ(1)       | Θ(1)         | Θ(1)      | Method calls, data swaps, loop iterations |
| [`insert()`](#insert) | O(n)       | O(n)         | Ω(1)      | Data swaps       |
| [`remove()`](#remove) | O(n)       | O(n)         | Ω(1)      | Data swaps       |


## When / why use an ArrayList?

From the time-complexity analysis, we can see that the array-list does
very well with its constant time lookup, and update. But, we see
its behavior degrades quickly with insertions and deletions at arbitrary
positions in the list. That being said, its good to use the array-list
when we do not know how many elements that we will have in the list beforehand;
but, over the course of time we will be doing more lookups and fetches than
insertions and deletions.

### A practical application

Let's say you are tasked with writing an average GPA calculator, where a student
can enter as many grades as they would like. How would you implement this?

## When / why not to use an ArrayList?

As mentioned, array-list do poorly with insertions and deletions. With that
in mind, we probably should not use an array-list with a list that is
changing often. If we have a list that is always growing, we will be doing
lots of copying and memory allocation, same for always shrinking. 

### A less practical application

You are writing a messaging application, and you have a master list
of everyone who is online. Every time a user logs in, or out, a function
is called that gives you the id number of the given user, and you must
either add them to the list or remove them from the list - respectively.
A better approach may be a [LinkedList][linked_list] or even a [Map][map].

## Conclusion

The ArrayList is a dynamically sized data structure that solves the problem
of not knowing how much space we will need beforehand, but still performs
best when used for lookup, and update and its size is not changing often.

[linked_list]: LinkedList.md
[map]: ../maps/Map.md