# DSA_Project
### @author Zigareen Kavuma Nakintu
### @version Java 24

## Description
This is a console-based application that implements a simple encode and decode system. The goal is to achieve decoding and encoding words that have been read into a file using a dictionary of words and symbols in a csv file .  The code encodes words into numerical values and decodes numerical values into words.

## To Run
From console at .jar file directory:

Java –enable-preview -cp ./indexer.jar ie.atu.sw.Runner.

Follow the on-screen menu:

 -Choose the option that will be used for mapping , Choose the option for the file you want to encode and then choose option to input the file name for the output file.

-Choose the encode or decode option.

The output will be written to the text file name that was input. The default output file is “./out.txt”. If it cannot be found, you will be prompted to add the file path for it. Check the file where the output was written to see the encode and decoded values.

The file path for all the files is denoted by “./encodings-10000.csv” or “./1984Orwell.txt”. 

## Features

•	The system uses a TreeMap   for mapping because the keys are automatically sorted that makes it easier and faster. This helped me not to use a sorting algorithm and then I stored words in an array for easy access.

•	Input path to dictionary csv file or stop words file including extension.

•	Used BufferedReader and FileWriter wrapped in try-catch blocks and ensures that files compute and exceptions can easily be identified.

## Testing

•	I tested the program by creating small several text files-Trial.txt containing words for encoding and decoding numbers.

•	I tested the output files manually to confirm the words and numbers were successfully entering the arrays and the map and vice versa.

## Conclusion
I have learned the importance of clear separation between encoding and decoding with better understanding of file handling in java and TreeMaps as well as Time and Space Complexity. If I were to extend the project, I would have worked with suffixes.

