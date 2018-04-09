# Things to Implement
List of things to implement, so that I don't forget

## Build
- Find some way to run `pip install -r requirements.txt` as well as 
install the python package `python-tk` if the development system is
linux based. Otherwise, `matplotlib` will fail
- Implement deploy script to upload the `java_data_structures.jar`
to a remote server or artifactory
- Change `build.gradle` to only do `javadoc` for `structures` package

## General
- Make `DataStructure` objects `extend Clonable` (if not too expensive of a change)
- Make instance variables on abstract classes protected rather than private. Provided that
extending classes still have access to the setter and getter methods, the variables are just 
as prone to being overwritten with bad values. However, adding extra function calls for getters
and setters just increases the call stack to grow unnecessarily.

## Trees
- Finish analytics testing `BinarySearchTree`
- Put time-complexity comments in `BinarySearchTree` (for main operations)

## Vectors
- Unit test `toArray()` functions in vectors

## Testing
- Functional testing using JUnit? Just for learning purposes?

## Useful
- http://etutorials.org/Programming/Java+analytics+tuning/Chapter+2.+Profiling+Tools/2.3+Method+Calls/