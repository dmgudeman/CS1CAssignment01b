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
      boolean checkLimitList = false;
      int target = 1000;

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
      // start timer
      startTime = System.nanoTime();
      
      // collect the dataset
      ArrayList<iTunesEntry> tunes = tunesInput.getTunes();
     
      // check to make sure there adequate resources in data set
      checkLimitList = checkLimitList(tunes, target);
      
      if (checkLimitList)
      {
         System.out.println("Creating powerset...");
         ArrayList<Sublist> powerset = makePowerset(tunes, target);

      }
      stopTime = System.nanoTime();

      // report algorithm time
      System.out.println("\nAlgorithm elapsed time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.\n");
   }

   public static ArrayList<Sublist> makePowerset(ArrayList<iTunesEntry> list,
         int target) throws CloneNotSupportedException
   {
      ArrayList<Sublist> powerset = new ArrayList<Sublist>();
     
      System.out.println("Powerset size1 =" + powerset.size());
     
      powerset.add(new Sublist(list));
     
      System.out.println("Powerset size2 =" + powerset.size());
      int t = 0;
      int kBest = 0;
      // for every item in the original list
      for (int i = 0; i < list.size(); i++)
      {

         ArrayList<Sublist> newPowerset = new ArrayList<Sublist>();
         System.out.println("newPowerset size " + newPowerset.size());
         
         System.out.println("target = " + target + "; sum = "
               + powerset.get(kBest).getSum() + "kBest = " + kBest
               + "highest idice = " + newPowerset.size() + "\n");
        
         for (Sublist subset : powerset)
         {    

            // copy all of the current powerset's subsets
            newPowerset.add(subset);
  //          System.out.println("newPowerset size " + newPowerset.size() + " t = " + t);
            t++;
              // plus the subsets appended with the current item
            Sublist newSubset = new Sublist(list);
            System.out.println("newSUBSET indices " + newSubset.getIndices());
           
            newSubset.getIndices().addAll(subset.getIndices());
 //           System.out.println("newSUBSET indices " + newSubset.getIndices());
            newSubset.addItem(i); 
  //          System.out.println("newSUBSET indices " + newSubset.getIndices());
            newPowerset.add(newSubset);
  //          System.out.println("newPowerset size " + newPowerset.size() + " t = " + t);
  //          System.out.println("Powerset size2 =" + powerset.size());
            
            kBest = findKBest(target, powerset);
   //         System.out.println("kBest =" + kBest);
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

   private static int findKBest(int target, ArrayList<Sublist> powerset)
   {
      int max = 0;
      int kBest = 0;
      int loop = powerset.size();
      System.out.println("Calculating kbest...powerset size " + powerset.size());
      for (int i = 0; i < loop; i++)
      {
         Sublist set = powerset.get(i);
         System.out.println("set get sum" + set.getSum());
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

   /**
    * This method checks to make sure there is adequate duration of songs to
    * achieve the target otherwise program completes
    */
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
            System.out.println("The target is " + target
                  + ". The most this song grouping can sum to is: " + limitSum
                  + ". Program end.");
            return false;
         }
         return true;
      }
   }
}
