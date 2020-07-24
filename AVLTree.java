import java.io.PrintWriter;

/* Liam Maguire 18344533 CSE2ALG */

//most methods are the same as used in the lab with the exception of StringInOrder

public class AVLTree<T extends Comparable<T> >
{
   private AVLNode<T> root;

   public AVLTree()
   {
      root = null;   
   }

   public AVLNode<T> getRoot(){
      return root;
   }

   public void displayElements(PrintWriter p)
   {
      displaySubtreePrefixOrder(root, p);
   }

   private void displaySubtreePrefixOrder(AVLNode<T> localRoot, PrintWriter p)
   {
      if (localRoot != null)
      {
         p.println(localRoot.getData());
         displaySubtreePrefixOrder(localRoot.getLeftChild(), p);
         displaySubtreePrefixOrder(localRoot.getRightChild(), p);
      }
   }

   public boolean insertElement(T data)
   {
      root = insertElement(root, data);
      return true;
   }

   private int height(AVLNode<T> localRoot)
   {
      if (localRoot == null) 
      {
         return -1;
      }
      else
      {
         return localRoot.getHeight();
      }
   }

   private AVLNode<T> insertElement(AVLNode<T> localRoot, T data)
   {
      if (localRoot == null)
      {
         localRoot = new AVLNode<T>(data);
      }
      else if (data.compareTo(localRoot.getData()) < 0)
      {
         AVLNode<T> leftChild = localRoot.getLeftChild();
         AVLNode<T> subtree = insertElement(leftChild, data);
         localRoot.setLeftChild(subtree);
         localRoot = rebalance(localRoot);
      }
      else 
      {
         AVLNode<T> rightChild = localRoot.getRightChild();
         AVLNode<T> subtree = insertElement(rightChild, data);
         localRoot.setRightChild(subtree);
         localRoot = rebalance(localRoot);
      }

      setHeight(localRoot);
      return localRoot;
   }

   private void setHeight(AVLNode<T> localRoot)
   {
      if (height(localRoot.getLeftChild()) > height(localRoot.getRightChild()))
      {
         localRoot.setHeight(height(localRoot.getLeftChild()) + 1);
      }
      else
      {
         localRoot.setHeight(height(localRoot.getRightChild()) + 1);
      }
   }

   public boolean removeElement(T data)
   {
      // not implemented
      return false;
   }

   public T contains(T searchElement){
   
      AVLNode<T> current = root;
      T temp = null;

      while (current != null)
      {
         if (current.getData().compareTo(searchElement) > 0)
         {
            current = current.getLeftChild();
         }
         else if (current.getData().compareTo(searchElement) < 0)
         {
            current = current.getRightChild();
         }
         else
         {
            temp = current.getData();
            return temp;
            
         }
         
      }
      return temp;
   }

   public void compare(AVLNode<T> current, T searchElement){

      if(current !=null){
         
         compare(current.getLeftChild(), searchElement);

         current.getData().compareTo(searchElement);
            
         

         compare(current.getRightChild(), searchElement);
         

      }
     
   }

   private AVLNode<T> rightRotation(AVLNode<T> node){
     
	   AVLNode<T> g = node;
      AVLNode<T> p = g.getLeftChild();
      AVLNode<T> rcp = p.getRightChild();
      p.setRightChild(g);
      g.setLeftChild(rcp);

      setHeight(g);
      return p;
   }

   private AVLNode<T> leftRotation(AVLNode<T> node){
      
	   AVLNode<T> g = node;
	  	AVLNode<T> p = g.getRightChild();
		AVLNode<T> lcp = p.getLeftChild();
		p.setLeftChild(g);
		g.setRightChild(lcp);

		setHeight(g);
      return p; 
   }

   private AVLNode<T> rightLeftRotation(AVLNode<T> node){

	   AVLNode<T> g = node;
      AVLNode<T> p = g.getRightChild();
		g.setRightChild(rightRotation(p));
      return leftRotation(g);
   }

   private AVLNode<T> leftRightRotation(AVLNode<T> node){

	   AVLNode<T> g = node;
	  	AVLNode<T> p = g.getLeftChild();
		g.setLeftChild(leftRotation(p));
      return rightRotation(g);
     
   }

   private int getHeightDifference(AVLNode<T> node){

	   AVLNode<T> leftChild = node.getLeftChild();
		AVLNode<T> rightChild = node.getRightChild();
		int leftHeight = leftChild == null? -1 : leftChild.getHeight();
		int rightHeight = rightChild == null? -1 : rightChild.getHeight();
		

      return leftHeight - rightHeight;
     
   }

   private AVLNode<T> rebalance(AVLNode<T> rootNode){

	   int diff = getHeightDifference(rootNode);

		if(diff < -1)
		{
			if(getHeightDifference(rootNode.getRightChild()) < 0)
			{
				rootNode = leftRotation(rootNode);
			}
			else
			{
				rootNode = rightLeftRotation(rootNode);
			}
		}
		else if(diff > 1)
		{
			if(getHeightDifference(rootNode.getLeftChild()) > 0)
			{
				rootNode = rightRotation(rootNode);
			}
			else
			{
				rootNode = leftRightRotation(rootNode);
			}

		}
		return rootNode;
   }
   
   //traverses the tree in order and displaying to screen
    private void displaySubtreeInOrder(AVLNode<T> localRoot){

 		if(localRoot != null)
 		{
 			displaySubtreeInOrder(localRoot.getLeftChild());
 			System.out.println(localRoot.getData());
 			displaySubtreeInOrder(localRoot.getRightChild());
 		}
   }

   //calls private display in order
   public void displayInOrder(){

		displaySubtreeInOrder(root);
   }

   //used for printing the tree in order
   private void printInOrder(PrintWriter printWriter, AVLNode<T> localRoot){

 		if(localRoot != null)
 		{
         printInOrder(printWriter, localRoot.getLeftChild());
          
         printWriter.println(localRoot.getData());
 			printInOrder(printWriter, localRoot.getRightChild());
 		}
   }

   //calls private printInOrder method
   public void printInOrder(PrintWriter printWriter){

		printInOrder(printWriter,root);
   }
   
   //method for displaying the Neighbours AVL tree in order
   private String StringinOrder (AVLNode<T> localRoot){

      if(localRoot != null){
         
         return (StringinOrder(localRoot.getLeftChild())+" " +  localRoot.getData()+ " " + StringinOrder( localRoot.getRightChild()) + " ").trim(); 
      }
      //to keep the compiler happy
      return "";
  }

  //calls the private StringInOrder method
  public String StringinOrder(){

     return "[" + StringinOrder(root) + "]";
  }

}
