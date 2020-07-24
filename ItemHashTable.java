import java.io.*;

/* Liam Maguire 18344533 CSE2ALG */

//most methods are the same as used in the labs except with custom findneighbour method and convert to avl method

public class ItemHashTable
{
   private Item[] itemArray;
   private int numberOfElements;

   private final double loadFactorBound = 0.5;

   //empty item
   private Item nonItem = new Item("");


   public ItemHashTable(){

	   itemArray = new Item[getPrime(10)];
	   numberOfElements = 0;

   }

   //generate hashkey
   private int hashFunction(String key){

	   int hashIndex = 0;
	   for (int index = 0; index < key.length(); index++){
		   hashIndex = 31 * hashIndex + key.charAt(index);
	   }
	   hashIndex = Math.abs(hashIndex) % itemArray.length;
	   
	   return hashIndex;
   }

   //inserts into hashtable
   public boolean insert(Item newPerson){
	   
	   if(getLoadFactor() >= loadFactorBound){
		   rehash();
	   }
	   
	   String key = newPerson.getWord();
	   
	   int hashIndex = hashFunction(key);
	   
	   while(true){
		   if(itemArray[hashIndex] == null || itemArray[hashIndex] == nonItem){
                itemArray[hashIndex] = newPerson;
			   numberOfElements++;
			   return true;
		   }
		   else{
			   hashIndex++;
			   hashIndex = hashIndex % itemArray.length;
		   }
      }
   }

   //searches hashtable for key
	public Item search(String key){
	   int hashIndex = hashFunction(key);
	   while(true){
		   if(itemArray[hashIndex] == null || itemArray[hashIndex] == nonItem){
			   return null;
		   }else if(itemArray[hashIndex].getWord().equals(key)){
			   return itemArray[hashIndex];
		   }
		   else{
			   hashIndex++;
			   hashIndex = hashIndex % itemArray.length;
		   }
	   }
	   
	}

   //not used
   public boolean remove(String key){
   
	  return false;
	}

   //displays or prints all elements
   public void displayElements(PrintWriter p){

	   for(int index = 0; index < itemArray.length; index++){
		   if(itemArray[index] != null && itemArray[index] != nonItem){
			   p.println(itemArray[index]);
		   }
	   }

   }

   public int getNumElements(){

      return numberOfElements;
   }

   private double getLoadFactor(){

      return numberOfElements/(double)itemArray.length;
   }

   private void rehash(){
       
       Item[] oldPersonArray = itemArray;
      int newArraySize = getPrime(oldPersonArray.length * 2);
      itemArray = new Item[newArraySize];
      numberOfElements = 0;

      for (int index = 0; index < oldPersonArray.length; ++index)
      {
         if (oldPersonArray[index] != null &&
             oldPersonArray[index] != nonItem)
         {
            insert(oldPersonArray[index]);
         }
      }
   }

   private int getPrime(int integer){

      while (!isPrime(integer))
      {
         integer++;
      }
      return integer;
   }

   private boolean isPrime(int integer){

      for(int n = 2; n * n <= integer; n++)
      {
			if(integer % n == 0)
			{
				return false;
			}
		}

		return true;
   }
   
   //searches hashtable for potential neighoburs of word
   public void findNeighbours(Item temp){

      String word = temp.getWord();

      //array of chars for every single alphabetical letter
      char[] alphabet = new char[] {
          'a','b','c','d','e','f','g','h','i',
          'j','k','l','m','n','o','p','q','r',
          's','t','u','v','w','x','y','z'
       };

       //for length of word search with eac
      for(int i = 0; i < word.length(); i++){
          
         //for each letter of alphabet replaces char i and searches
         for(int j = 0; j < alphabet.length; j++){
            
            // search string replaces one letter with all possible options before searching
            String searchString = word.substring(0,i) + alphabet[j] + word.substring(i+1);
            //searches to see if neighbour exists
            Item item = search(searchString);
            // if search returns value adds to eachothers neighbours
            if(item != null){
               //adds to neighbours of both
               temp.neighbourCheck(item);
               item.neighbourCheck(temp);
            }
         }
      }
  }

  //iterates through hashtable and adds to avltree before returning as avl tree as can be displayed in order
  public AVLTree<Item> convertToAVL( ){

      AVLTree<Item> temp = new AVLTree<>();

	   for(int index = 0; index < itemArray.length; index++){
		   if(itemArray[index] != null && itemArray[index] != nonItem){
			   temp.insertElement(itemArray[index]);
		   }
      }
      
      return temp;
   }
}