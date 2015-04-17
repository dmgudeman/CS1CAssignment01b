import java.util.ArrayList;

import cs_1c.iTunesEntry;

class Sublist implements Cloneable
{
   private int sum = 0;
   private ArrayList<iTunesEntry> originalObjects;
   private ArrayList<Integer> indices;
   
   public ArrayList<Integer> getIndices() {
      return indices;
   }
   
   public void setIndices(ArrayList<Integer> indices) {
      this.indices = indices;
   }
   
   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<iTunesEntry> list) 
   {
      sum = 0;
      originalObjects = list;
      indices = new ArrayList<Integer>();
   }
   
   public int getSum()
   { 
      return sum;
   }

   public void setSum(int sum)
   {  
      if (sum >= 0) this.sum = sum;
      
   }

   // I have done the clone() for you, since you will need clone() inside addItem().
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      Sublist newObject = (Sublist)super.clone();
      // deep copy
      newObject.indices = (ArrayList<Integer>)indices.clone();
      return newObject;
   }
   
   Sublist addItem(int indexOfItemToAdd) throws CloneNotSupportedException
   {  
      Sublist s = (Sublist) clone();
      
      // wrapper for the primitive variable
      Integer intObj = new Integer(indexOfItemToAdd);
    s.indices.add(intObj);
      System.out.println("sindice + " +  s.indices.add(intObj));
      
      s.sum = originalObjects.get(indexOfItemToAdd).getTime() + this.sum;
      
      return s;
   }
     
   void showSublist(){ 
    
      for (int i = 0; i < indices.size(); i++) {
       
         System.out.print((i ) + ". [" + i + "]" +
             " [" + originalObjects.get(i).getTitle() + "]"+
             " [" + originalObjects.get(i).getTime() + "] \n");    
      }
   }

 
};