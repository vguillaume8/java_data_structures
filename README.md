# Data Structures

This is project is a tutorial that covers common data structures that are
seen in introductory and intermediate computer science courses. The site contains
explanations, diagrams, and mathematical proofs for each data structure. In addition
to the online tutorials, the data structures can be downloaded as a 
[Java 8 library][jar_file] for
readers to test with their own code.

## Prerequisites

This tutorial assumes that the reader has either taken or is taking an introduction to
programming course or has the equivalent knowledge. Knowing a the Java 
programming language would be useful for reading code examples, but the
idea behind the data structures are language agnostic.

The core programming concepts that the reader should know include:

* [Arrays][arrays]
* [Variables][variables]
* [Methods / Functions][methods]
* [Loops][loops]
* [Recursion][recursion]
* [Generic types (Java specific)][generic_types]
* [Runtime Complexity / Big-O-Notation][runtime_complexity]

[arrays]: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html
[variables]: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html
[methods]: https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html
[loops]: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html
[recursion]: https://en.wikipedia.org/wiki/Recursion_(computer_science)
[generic_types]: https://docs.oracle.com/javase/tutorial/java/generics/types.html
[runtime_complexity]: https://en.wikipedia.org/wiki/Time_complexity

## What will be covered

The following categories will be covered by this project:

* [Vectors](#vectors)

* [Trees](#trees)

* [Sets](#sets)

* [Maps](#maps)

* [Graphs](#graphs)

## How the project is structured

### High level

The project is broken into sections, starting from the simpler data structures going
towards the more complex structures. This project aims to make each tutorial module as
independent and non-reliant on other modules as possible. But, by the nature of some of
the data structures, there is some overlap and interdependence. 

### Individual modules

Each module, let's say, for example, Vector is broken up into at least one tutorial
submodule for each type of vector. For example, there are 4 different types of vectors,
thus, the vector module has 4 submodules that covers each data structure individually.

### Submodules

Submodules are the core of this project. Each submodule goes into great detail about
a given data structure. They come with a high-level idea of the data
structure under investigation, detailed explanations for each of its basic and non-trivial utility
operations (with time-complexity analysis), basic code examples for implementing 
the structures, and examples for both good and less-optimal applications of the given structure.

**Note:** Although this project provides full implementations of the data structures, the code
examples in the text will be smaller, more basic implementations that use only the Java Standard 
Library. This was a design decision in that a reader can just copy and paste the code examples
from the text, run, and modify them without having to install the entire Java library.

## Modules

The project is divided into the following modules.

### <a name="vectors"></a> Vectors

Vectors are any data structure that takes a linear form. These are data 
structures that are used to represent lists, sequences, or provide some 
sort of ordering to data. [Learn more about Vectors...][vector]

### <a name="trees"></a> Trees

Trees are non-linear data structures often used for keep data organized.
We typically use trees when the data that we are working with can be 
sorted / ordered. [Learn more about Trees...][tree]

### <a name="sets"></a> Sets

A Set is a dynamically sized group of unordered unique elements.
Finding an application for a set may seem more challenging, 
when we can just put them into some sort of list. The advantage 
comes in the speed of lookup and insertion.
[Learn more about Sets...][set]

### <a name="maps"></a> Maps

Maps are like dictionaries. They are unordered, dynamically sized
groups of unique key to non-unique value mappings - hence the name, 
Map. Maps have many applications, such as implementing dictionaries 
(key is to word, as value is to definition).
[Learn more about Maps][map]

### <a name="graphs"></a> Graphs

Graphs are an abstract data structure with tons of applications.
In essence, a graph is a set of objects that are (potentially)
connected to other objects in the set. A good application of a graph
is to represent a highway system between cities.
[Learn more about Graphs...][graph]

## The API

All of the aforementioned data structures are implemented in the Java 
programming language. The code is available as a library in the following formats:

 * [Source code GitHub][github_repo].
 * [Java Executable Binary][jar_file]
 
 **Note:** The above library is united tested, functionally tested, and performance
 tested to be as consistent with standard implementations of data structure. However,
 this project is for *educational purposes*, and thus the implementation has not been
 tested for scalable applications. Do not use these data structures in mission critical 
 applications.
 
 For more information on about the API, please
 see the following links.

[Setup and Installation][setup_md]

[API Documentation][javadoc]


[jar_file]: http://jabaridash.com:8085/build/java_data_structures.jar
[setup_md]: docs/dev/setup/SETUP.md
[javadoc]: docs/javadoc/index.html
[vector]: docs/structures/vectors/Vector.md
[tree]: docs/structures/trees/Tree.md
[graph]: docs/structures/graphs/Graph.md
[map]: docs/structures/maps/Map.md
[set]: docs/structures/sets/Set.md

[github_repo]: https://github.com/N02870941/java_data_structures

