# Occurrences Counter

Write a program that counts the occurrences of alphabetic characters (letters "A" through "Z") in a set of text files.

The program takes as input a series of text file paths and for each of them counts the occurrences of the characters,
ignoring any non-alphabetic characters (such as the digits 0 to 9).

For each file, the counting is carried out by a specific task and all activated tasks are managed through a thread pool.<br>
Tasks record their partial results within a ConcurrentHashMap.

Before finishing, the program prints the number of occurrences of each character on a specific output file.<br>
The output file contains one line for each character and is formatted as follows:<br>

`character,occurrences count`

## Usage

- Extract **_RaccoltaTesti.zip_** in the main folder

- Compile

```
javac ./src/*.java
```

- Run

```
java src.OccurrencesCounter <textfile1> <textfile2> ... <textfileN>
```
