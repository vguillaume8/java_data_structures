# ArrayList

An array list is a common data structure used in many programming languages.
In other programming languages, it may be called something else, but most
programming languages should have them. Array-lists are alternatives to arrays.

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

**Definition:** An array-list is a data structure that stores values into an array, where
if the array becomes full, a new copy of the array is made with extra space at the end
so that insertion can continue.

## When / why use an ArrayList?


## When / why not to use an ArrayList?

Like this, like this

### Finding elements

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

Where `elements` is the internal array.

### (The essence of)  inserting elements into an ArrayList

Inserting an element is a little more difficult. Assume, we have an array-list `names` 
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

### Removing elements 

## Applications of ArrayLists

## Performance


