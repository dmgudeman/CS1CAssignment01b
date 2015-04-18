import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import cs_1c.iTunesEntry;
import cs_1c.iTunesEntryReader;

//------------------------------------------------------
public class Foothill
{
   // ------- main --------------
   public static void main(String[] args) throws Exception
   {

      int target = 1000;
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
      int maxSum = 0;
      int currentSum = 0;
      int currentSum2 = 0;
      int count = 0;
      int x = -1;
      boolean done = false;
      ArrayList<Sublist> Col = new ArrayList<Sublist>();
      Col.add(new Sublist(tunes)); // add the empty set
     
     
      outerloop:
      // for every item in the original list
      for (int j = 0; j < Col.size(); j++)
      {    
//         if (done = true) break outerloop;
      
         for (int i = 0; i < tunes.size(); i++)
         {  
            currentSum = Col.get(j).getSum() + tunes.get(i).getTime();
          
          
            if ( currentSum <= target || currentSum < maxSum)
            {             
               {
                  Col.add(Col.get(j).addItem(i));

                  System.out.println("Current Sum  IN MIDDLE"
                        + Col.get(j).getSum());
               }
               if (currentSum == target)
               {
                  break outerloop;
               } else if (currentSum > maxSum)
               {
                  maxSum = currentSum;
                  done = false;
                  System.out.println("maxSum = " + maxSum + ", indexOfMax = "
                        + indexOfMax
                        + "count =****************************************"
                        + count);
               }

               count++;

            }
         
         }
       //  Col.get(index)
         System.out.println("THis is printed");
         System.out.println("THIS IS the end"
               + Col.get(count).getIndices() + "\n");
         Col.get(count).showSublist();
      }
   
     

      {
       
       

      }

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
