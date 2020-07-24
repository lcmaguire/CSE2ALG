import java.util.*;

/* Liam Maguire 18344533 CSE2ALG */

public class Item implements Comparable<Item>
{
	private String word;
	private int count;
	private AVLTree<String> neighbours;
	
	public Item(String word){
		this.word = word;
		count = 1;
		//neighbours = new ArrayList<String>();
		neighbours = new AVLTree<String>();
	}
	
	public String getWord(){
		return word;
	}
	
	public int getCount(){
		return count;
	}

	public void incrementCount(){
		count++;
	}
	
	public AVLTree<String> getNeighbours(){
		return neighbours;
	}
	
	//used for WordMatchTester to display word + count but not neighbours
	public String info(){
		return word + " " + count;
	}
	
	//to string method, calls avltree StringInOrder method and replaces spaces with ", " to give same format as arrayList
	public String toString(){
		return word + " " + count + " " + neighbours.StringinOrder().replaceAll(" ", ", ");
	}
	
	//compareTo method
	public int compareTo(Item item){
		return this.word.compareToIgnoreCase(item.word);
		
	}

	//checks neighbour isnt already in tree then adds
	public void neighbourCheck(Item item){

		if(neighbours.contains(item.getWord()) == null){
			neighbours.insertElement(item.getWord());
		}
	}

}