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

      int target = 22109;
      boolean checkLimitList = false;
   
      // for formatting and timing
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long startTime, stopTime;

      // read the iTunes Data
      iTunesEntryReader tunesInput = new iTunesEntryReader(
            "itunes_file.txt");

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
      if (checkLimitList)
      {
      ArrayList<Sublist> powerset = makePowerSet(tunes, target);
      }
      stopTime = System.nanoTime();

      // report algorithm time
      System.out.println("\nAlgorithm elapsed time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.\n");
   }

   /**
    * Make the powerset
    */
   public static ArrayList<Sublist> makePowerSet(ArrayList<iTunesEntry> tunes,
         int target) throws CloneNotSupportedException
   { 
      int maxTime = 0;
      int indexOfMax = 0;
      
      ArrayList<Sublist> Col = new ArrayList<Sublist>();
      Col.add(new Sublist(tunes)); // add the empty set
      
      outerloop:
      // for every item in the original list
      for (int i = 0; i < tunes.size(); i++)
      {
         for (int j = 0; j < Col.size(); j++)
         {
            if ((Col.get(j).getSum() + tunes.get(i).getTime()) <= target)
            {
               Col.add(Col.get(j).addItem(i));
            }
            if ((Col.get(j).getSum() + tunes.get(i).getTime()) == target)
            {
               break outerloop;
            }
         }
      }
      for (int i = 0; i < Col.size(); i++)
      {
         if (Col.get(i).getSum() > maxTime)
         {
            indexOfMax = i;
            maxTime = Col.get(i).getSum();
            System.out.print("\nwith sum of " + Col.get(i).getSum() + "\n");
         }        
      } 
      Col.get(indexOfMax).showSublist(); 
     
      return Col;
   }

   /**
    * helper function to screen for an inadequate dataset
    */
   public static boolean checkLimitList(ArrayList<iTunesEntry> tunes, 
         int target) throws CloneNotSupportedException
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
