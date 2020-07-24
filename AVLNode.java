/* Liam Maguire 18344533 CSE2ALG */

//methods are the same as used in the lab

public class AVLNode<T extends Comparable<T> >
{
   private T data;
   private AVLNode<T> leftChild;
   private AVLNode<T> rightChild;
   private int height;

   public AVLNode(T data)
   {
      this.data = data;
      leftChild = null;
      rightChild = null;
      height = 0;
   }

   public void setLeftChild(AVLNode<T> leftChild)
   {
      this.leftChild = leftChild;
   }
   
   public void setRightChild(AVLNode<T> rightChild)
   {
      this.rightChild = rightChild;
   }

   public void setData(T data)
   {
      this.data = data;
   }

   public void setHeight(int height)
   {
      this.height = height;
   }

   public AVLNode<T> getLeftChild()
   {
      return leftChild;
   }
   
   public AVLNode<T> getRightChild()
   {
      return rightChild;
   }

   public T getData()
   {
      return data;
   }

   public int getHeight()
   {
      return height;
   }
}
