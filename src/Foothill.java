import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.text.html.HTMLDocument.Iterator;

import cs_1c.iTunesEntry;
import cs_1c.iTunesEntryReader;

//------------------------------------------------------
public class Foothill
{
   // ------- main --------------
   public static void main(String[] args) throws Exception
   {

      int target = 1200;
      boolean checkLimitList;

      // for formatting and timing
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long startTime, stopTime;

      // read the iTunes Data
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file_small.txt");

      // test the success of the read:
      if (tunesInput.readError())
      {
         System.out.println("couldn't open " + tunesInput.getFileName()
               + " for input.");
         return;
      }
      startTime = System.nanoTime();
      ArrayList<iTunesEntry> tunes = tunesInput.getTunes();

      checkLimitList = checkLimitList(tunes, target);

      // make the power set
      ArrayList<Sublist> powerset = makePowerSet(tunes, target);
      stopTime = System.nanoTime();

      // report algorithm time
      System.out.println("\nAlgorithm elapsed time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.\n");
   }

   /**
    * Make the powerset
    */
   public static ArrayList<Sublist> makePowerSet(ArrayList<iTunesEntry> list,
         int target) throws CloneNotSupportedException
   {
      int newSum = 0;
      int maxTime = 0;
      ArrayList<Sublist> Col = new ArrayList<Sublist>();
      Col.add(new Sublist(list)); // add the empty set
      Sublist S = new Sublist(list);
      Sublist L = new Sublist(list);
     

      // for every item in the original list
      for (int i = 0; i < list.size(); i++)
      {  System.out.println("i = " +i);
     
      for (int j =0; i < Col.size(); j++)
         {   
          Col.get(j);
      
            newSum = L.getSum();
            
            if (newSum == target)
            {
               L.showSublist(); 
              return Col;
               
            }
            if (newSum < target && newSum > maxTime)
            {
               maxTime = newSum;
            }
            
            if (newSum <= target)
            {
            L.addItem(j);
            int c = L.getSum();
            Col.add(L);
          
             System.out.println("maxTime is:" + maxTime); 
             L.showSublist(); 
            }        
         
            }
        
      }
      
      L.showSublist(); 
      return Col;
   }

   
   /**
    * helper function to screen for an inadequate dataset
    */
   public static boolean checkLimitList(ArrayList<iTunesEntry> tunes, int target)
         throws CloneNotSupportedException
   {
      int limitSum = 0;

      for (int i = 0; i < tunes.size(); i++)
      {
         limitSum += tunes.get(i).getTime();
      }
      if (limitSum <= target)
      {
         System.out.println("The target is: + " + target);
         System.out
               .println("The most this grouping can sum to is: " + limitSum);
         System.out.println("Program stopped.");
         return false;
      }
      return true;
   }
}
