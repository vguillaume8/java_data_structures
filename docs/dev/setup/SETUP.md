# Setup and Installation

[![Build Status](https://travis-ci.org/N02870941/java_data_structures.svg?branch=vectors)](https://travis-ci.org/N02870941/java_data_structures)

## Dependencies
This project aims to have as few third-party dependencies as possible.

Click the below dependencies to figure out how to install them if they are
not already installed.

#### Languages

This project uses two programming languages. The core data structures are
implemented in Java, and the tests are written in
Groovy.

- Java 8 (development)
- Groovy (gradle and testing)

#### Command Line Tools (CLI)

Build automation and compilation is handled by the gradle CLI.

- gradle (build automation)

#### APIs / JARs

All other dependencies such as APIs and JARs will be automatically 
downloaded by gradle when the project is built.

## Installation

This project uses gradle as a build system; therefore, getting started on this
project is as simple as cloning the repository, and opening the project with
your favorite IDE or text editor.

To test it out on the command line, execute the following:

```bash
git clone https://github.com/N02870941/java_data_structures.git

cd java_data_structures

gradle build

gradle run -q
```

## Un-installation
You can simply delete the `java_data_structures` folder, as this is a self-contained
project, and all dependencies will be deleted along with the folder. However, 
Java 8, and Groovy must be uninstalled (and initially installed) separately.

```bash
# Maybe delete  the folder in Windows Explorer
# or Finder, because the following command could be
# dangerous and is irreversible

rm -rf java_data_structures
```
