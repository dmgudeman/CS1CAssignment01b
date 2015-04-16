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

      int target = 3600;
      boolean checkLimitList;

      // for formatting and timing
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long startTime, stopTime;

      // read the iTunes Data
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file.txt");

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
      ArrayList<Sublist> powerset = new ArrayList<Sublist>();
      powerset.add(new Sublist(list)); // add the empty set
      int maxSum = 0;
      int kBest = 0;
 
     
      while (maxSum <= target)
         {
              // for every item in the original list
             for (int i = 0; i < list.size(); i++)
             {
                ArrayList<Sublist> newPowerset = new ArrayList<Sublist>();

                for (Sublist subset : powerset)
                {  
             
               
            
                   // copy all of the current powerset's subsets
                   newPowerset.add(subset);

                   // plus the subsets appended with the current item
                   Sublist newSubset = new Sublist(list);
                   newSubset.getIndices().addAll(subset.getIndices());
                   newSubset.addItem(i); //
           
                     newPowerset.add(newSubset);
                    kBest = findKBest(target, powerset);
           
                    System.out.println("This is kTemp " + kBest);         
                 }    
                powerset = newPowerset;
               }  
            
           }
           return powerset;
      }

   /**
    * This filters the powerset for the highest value
    */
   private static int findKBest(int target, ArrayList<Sublist> powerset)
   {
      int max = 0;
      int kBest = 0;
      for (int i = 0; i < powerset.size(); i++)
      {
         Sublist set = powerset.get(i);
         int sum = set.getSum();
         if (sum == target )
         {
            kBest = i;
            System.out.println("The target is " + target + "\n");
            powerset.get(kBest).showSublist();

            System.out.println("\nwith sum of " + powerset.get(kBest).getSum());
            return kBest;
         } else
         {
            if (sum > max && sum <= target)
            {
               max = sum;
               kBest = i;
            }
         }
      }
      return kBest;
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
