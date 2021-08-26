# SPAN

## Prerequisites:
* Java 8

## Tech used:
Java 8
Gradle
JUnit

## How to run:
1. Navigate to project base dir
2. gradlew clean test
3. gradlew clean jar
4. Navigate to ${basedir}/build/libs
5. run java -jar SPAN-1.0-SNAPSHOT.jar
6. Follow console instructions to either use a file for input or manually input data

## Considerations
1. Validate input data in terms of types
2. increase test coverage

The sample input file can be found in ${basedir}/src/test/resources/sample-input
