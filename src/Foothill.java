import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import cs_1c.iTunesEntry;
import cs_1c.iTunesEntryReader;

//------------------------------------------------------
public class Foothill
{
   public static ArrayList<Sublist> getPowerSetUpToTarget(
         ArrayList<iTunesEntry> list, int target)
         throws CloneNotSupportedException
   {
      ArrayList<Sublist> powerset = new ArrayList<Sublist>();
      powerset.add(new Sublist(list)); // add the empty set
      int kBest = 0;
      // for every item in the original list
      for (int i = 0; i < list.size(); i++)
      {

         ArrayList<Sublist> newPowerset = new ArrayList<Sublist>();
         System.out.println("target = " + target + "; sum = "
               + powerset.get(kBest).getSum() + "kBest = " + kBest
               + "highest idice = " + newPowerset.size());
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

            if (powerset.get(kBest).getSum() == target)
            {
               return newPowerset;
            }
         }

         // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
         powerset = newPowerset;
      }

      return powerset;
   }



   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      boolean checkLimitList = false;

      int target = 2000;
     
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
      startTime=System.nanoTime();
      ArrayList<iTunesEntry> tunes = tunesInput.getTunes();
      checkLimitList = checkLimitList(tunes, target);

      if (checkLimitList)
      {
         System.out.println("Creating powerset...");
         ArrayList<Sublist> powerset = getPowerSetUpToTarget(tunes, target);

      }
      stopTime = System.nanoTime();

      // report algorithm time
      System.out.println("\nAlgorithm elapsed time: "
         + tidy.format((stopTime - startTime) / 1e9)
         + " seconds.\n");
   }

   private static int findKBest(int target, ArrayList<Sublist> powerset)
   {
      int max = 0;
      int kBest = 0;
      int loop = powerset.size();
      System.out.println("Calculating kbest...");
      for (int i = 0; i < loop; i++)
      {
         Sublist set = powerset.get(i);
         int sum = set.getSum();
         if (sum == target)
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
               System.out.println("inside the sum > max loop target = "
                     + target + " sum is: " + sum + " max is " + max
                     + " powerest size = " + powerset.size());
               

            }
         }
      }

      return kBest;
   }

   public static boolean checkLimitList(ArrayList<iTunesEntry> list, int target)
         throws CloneNotSupportedException
   {
      
      {
         int limitSum = 0;

         for (int i = 0; i < list.size(); i++)
         {
            limitSum += list.get(i).getTime();
           
         }
         if (limitSum <= target)
         {
            System.out.println("The most this grouping can sum to is: "
                  + limitSum);
            return false;
         }
         return true;
      }

   }
}
