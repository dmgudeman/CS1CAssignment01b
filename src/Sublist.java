import java.util.ArrayList;

class Sublist implements Cloneable
{
   private int sum = 0;
   private ArrayList<Integer> originalObjects;
   private ArrayList<Integer> indices;
   
   public ArrayList<Integer> getIndices() {
      return indices;
   }
   
   public void setIndices(ArrayList<Integer> indices) {
      this.indices = indices;
   }
   
   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<Integer> list) 
   {
      sum = 0;
      originalObjects = list;
      indices = new ArrayList<Integer>();
   }
   
   int getSum() {
      sum = 0;
      for (int i = 0; i < indices.size(); i++) {
         sum += originalObjects.get(indices.get(i));
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
         int indice = indices.get(i);
         System.out.print(originalObjects.get(indice) + ", [" + indice + "] \n");
       
      }
      System.out.println("}");
   }
};