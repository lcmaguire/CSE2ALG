import java.util.*;
import java.io.*;
import java.util.regex.*;  

/* Liam Maguire 18344533 CSE2ALG */
public class WordMatchTester{


	public static void main(String [] args)throws Exception {

		//warning for if program doesnt have the required text arguments
		if (args.length != 4)
		{
		   System.out.println("Usage: WordMatchTester <input file> <output file> <input file 2> <output file 2>");
		   return;
		}

		//infile containing names of text files to be read in
		String inFile1 = args[0];
		//outfile name for lexicon to be written to
		String outFile1 = args[1];
		//infile for patterns to be checked
		String inFile2 = args[2];
		//outfile for patterns to be matched
		String outFile2 = args[3];

		//hashtable initialized and data read in
		ItemHashTable hash = new ItemHashTable();
		readLexiconInData(hash, inFile1);

		//printwriter to write data to file
		PrintWriter printWriter = new PrintWriter(new File(outFile1));
		
		//hash table converted to avl tree and displayed
		AVLTree<Item> avl = hash.convertToAVL();
		avl.printInOrder(printWriter);

		//closes printwriter
		printWriter.close();

		readInPatternData(avl, inFile2, outFile2);

	}

	//reads names of text file and then calls the loadData method
	public static void readLexiconInData(ItemHashTable hash, String inputFile) throws IOException{

		Scanner infile = new Scanner(new File(inputFile));

		//while infile has next line will read name of text file and load data from it
		while(infile.hasNext()){
			String file = infile.nextLine();
			file = file.trim();

			//if string isnt empty loaddata
			if(!file.equals("")){
				loadData(file, hash);
			}
		  	  
		}
		//closes scanner
		infile.close();
	}


	//loads data from text file into hashtable
	public static void loadData(String file,  ItemHashTable hash) throws IOException {
		
		Scanner infile = new Scanner(new File(file));
		
		while(infile.hasNext()){
		
			String line = infile.nextLine();
			line = line.trim();
			//splits line into strings seperated by spaces
			String [] temp = line.split(" ");
		  
			for(int i = 0; i < temp.length; i++){
				//convert to lowercase
				temp[i] = temp[i].toLowerCase();
				//removes all non alphabetic characters
				temp[i] = temp[i].replaceAll("[^a-z]","");
				//removes any whitespace
				temp[i] = temp[i].trim();

			  	//if not empty will check if exists
			  	if(!temp[i].equals("")){
				  
					Item t = new Item(temp[i]);
					Item hashTemp = hash.search(t.getWord());
					if(hashTemp == null){
						//searches item for all neighbours
						hash.findNeighbours(t);
						//inserts item after searching neighbours to not worry about added to its own neighbours
						hash.insert(t);
						
					}else{
						//word already in table increment count of word
						hashTemp.incrementCount();					
					}
			
			  	}
			}
		  
		}

		//closes scanner
		infile.close();
	}

	//reads in patterns, checks for patterns adds to tree and then prints to textfile
	public static void readInPatternData(AVLTree<Item> avl, String inputFile, String outFile) throws IOException{
		//file for patterns to be read from
		Scanner infile = new Scanner(new File(inputFile));

		//outfile
		PrintWriter printWriter = new PrintWriter(new File(outFile));
		

		while(infile.hasNext()){
			//assigns and removes any whitespace from pattern
			String pattern = infile.nextLine();
			pattern = pattern.trim();

			//if pattern not empty checks pattern and adds to tree before printing pattern file
			if(!pattern.equals("")){
				
				AVLTree<Item> match = new AVLTree<Item>();
				patternCheck(avl.getRoot(), match, pattern);
			  	printWriter.println(pattern);
			  	match.printInOrder(printWriter);
			  	printWriter.println();
			} 	  
		}
		//closes scanner and printwriter
		infile.close();
		printWriter.close();
	}

	//traverses tree and checks if patterns are the same
	public static void patternCheck(AVLNode<Item> localRoot, AVLTree<Item> match, String pattern){
		 
		 if (localRoot != null){

			if(patternCheck(pattern, localRoot.getData().getWord())){
				match.insertElement(localRoot.getData());
			 }

			patternCheck(localRoot.getLeftChild(),match, pattern);
			patternCheck(localRoot.getRightChild(),match, pattern);
		  }

	}

	//converts pattern to lowercase and to regex pattern before checking if matches
	public static boolean patternCheck(String pattern, String word){
		
		if(word == null){
			return false;
		}

		pattern = pattern.toLowerCase();
		pattern = pattern.trim();
		//replaces wildcard characters with regex equivalent 
		pattern = pattern.replace("*","\\w*");
		pattern = pattern.replace("?","\\w");
		
		return Pattern.matches(pattern,word);
		
	}

}