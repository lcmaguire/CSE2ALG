# CSE2ALG
Algorithms and Data Structures assignment 2

Final Assignment for CSEALG2

program reads from a list of text files another list of text files from which it will create a lexicon 

The lexicon will consist of each unique word ordered alphabetically, record number of occurances for each as well as neighbours (words of same length with one character different).

The Lexicon will be written to outFile1.txt

inFile2.txt contains a list of regex patterns to search the lexicon for all words that match.
outFile2.txt will contain all matching words including count and neighbours.

this is done through reading each word into a hashtable, checking if unique as well as its neighbours on insertion and updating each words neighbour and count when required.
once lexicon is formed it is then written to an AVLTree to sort it before writing to a file.

to compile run Javac WordMatchTester.java

to run Java WordMatchTester inFile1.txt outFile1.txt inFile2.txt outFile2.txt

inFile1 contains list of names of text files to be read in
outfile1 name for lexicon to be written to
infile2 for regex patterns to be checked from within lexicon
outfile2 for patterns found to be matched from in the lexicon.
