import java.util.ArrayList;

import cs_1c.iTunesEntry;

class Sublist implements Cloneable
{
   private int sum = 0;
   private ArrayList<iTunesEntry> originalObjects;
   private ArrayList<Integer> indices = new ArrayList<Integer>();
   
   public ArrayList<Integer> getIndices() {
      return indices;
   }
   
   public boolean setIndices(ArrayList<Integer> indices) {
      this.indices = indices;
      return true;
   }
   
   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<iTunesEntry> list) 
   {
      sum = 0;
      originalObjects = list;
   }
   
   int getSum() {
      sum = 0;
      for (int i = 0; i < indices.size(); i++) {
         sum += originalObjects.get(indices.get(i)).getTime();
      }
      return sum;
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
   
   Sublist addItem( int indexOfItemToAdd ) throws CloneNotSupportedException
   { 
     indices.add(indexOfItemToAdd);
    Sublist s = (Sublist) clone(); // maybe?
     return s;
     
   }
     
   void showSublist(){ 
      System.out.print("{");
      for (int i = 0; i < indices.size(); i++) {
         Integer indice = indices.get(i);
         System.out.print(i + ", [" + indice + "] \n");
         System.out.print(i + ". TITLE [" + originalObjects.get(indice).getTitle() + "]  ARTIST [" + originalObjects.get(indice).getArtist() + "]  TIME [" + originalObjects.get(indice).getTime() + "] \n");
       
      }
      System.out.println("}");
   }
};