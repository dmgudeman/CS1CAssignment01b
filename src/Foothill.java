import java.util.ArrayList;

import cs_1c.*;

import java.text.*;
import java.util.*;

//------------------------------------------------------
public class Foothill
{
   public static ArrayList<Sublist> getPowerSetUpToTarget(ArrayList<iTunesEntry>
      list, int target) throws CloneNotSupportedException
   {  
      ArrayList<Sublist> powerset = new ArrayList<Sublist>();
      powerset.add(new Sublist(list)); // add the empty set
      int kBest =0 ;
      // for every item in the original list
      for (int i = 0; i < 300; i++) //list.size(); i++)
      {
         
         ArrayList<Sublist> newPowerset = new ArrayList<Sublist>();
         System.out.println("target = " + target + "; sum = " + powerset.get(kBest).getSum() + "kBest = " + kBest + "highest idice = " + newPowerset.size());
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
            
            if (powerset.get(kBest).getSum() == target) {
               return newPowerset;
            }
         }

         // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
         powerset = newPowerset;
      }

      return powerset;
   }

   // ------------------------------------------------------

   // ------- main --------------
   public static void main(String[] args) throws Exception
   {    boolean checkLimitList = false;

    //  ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();
      //ArrayList<Sublist> powerset = new ArrayList<Sublist>();
      
   //   int k, j, numSets, arraySize, masterSum;
      int target = 2000;
     // boolean foundPerfect;

      // for formatting and timing
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
   //   long startTime, stopTime;

      // read the iTunes Data
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file.txt");

      // test the success of the read:
      if (tunesInput.readError())
      {
         System.out.println("couldn't open " + tunesInput.getFileName()
               + " for input.");
         return;
      }
      
      ArrayList<iTunesEntry> tunes = tunesInput.getTunes();
      checkLimitList = checkLimitList(tunes, target);

      if (checkLimitList)
      {

      System.out.println("Creating powerset...");
      ArrayList<Sublist> powerset = getPowerSetUpToTarget(tunes, target);
      
//      findKBest(target, powerset);

      // load the dataSet ArrayList with the iTunes:
//      arraySize = tunesInput.getNumTunes();
//      for (k = 0; k < arraySize; k++)
//         dataSet.add(tunesInput.getTune(k));
//      ArrayList<Sublist> powerset = makePowerset(dataSet);
//      for (int i = 0; i < powerset.size(); i++)
//      {
//         Sublist set = powerset.get(i);
//
//         int sum = set.getSum();
//
//         if (sum == target)
//         {
//            kBest = i;
//
//            break;
//         } else
//         {
//            if (sum > max && sum <= target)
//            {
//               max = sum;
//               kBest = i;
//            }
//         }
//      }

//      System.out.print("A best set is: \n\n");
//      powerset.get(kBest).showSublist();
//      System.out.print("\nwith sum of " + powerset.get(kBest).getSum());
//     // powerset.clear();
//      System.out.println("Target time: " + target);
//
//      // code supplied by student
//
//      // choices.get(kBest).showSublist();
//      System.out.print("Best set is: ");
//      powerset.get(kBest).showSublist();
//      System.out.print(" with sum of " + powerset.get(kBest).getSum());
//      System.out.println(powerset.isEmpty());
//      for (int i = 0; i < dataSet.size(); i++)
//      {
//         System.out.println(dataSet.get(i).getTime());
//      }
   }
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
            System.out.println("The target is "+ target +"\n");
            powerset.get(kBest).showSublist();
            
            System.out.println("\nwith sum of " + powerset.get(kBest).getSum());
            return kBest;
         } else
         {
            if (sum > max && sum <= target)
            {  
               max = sum;
               kBest = i;
               System.out.println("inside the sum > max loop target = " + target + " sum is: " + sum + " max is " + max + " powerest size = " +  powerset.size());
               
            }
         }
      }
      return kBest;
   }
   
   public static boolean checkLimitList(ArrayList<iTunesEntry> list, int target)
         throws CloneNotSupportedException
   {
      // ArrayList<Sublist> powerset = new ArrayList<Sublist>();
      // powerset.add(new Sublist(list)); // add the empty set
      {
         int limitSum = 0;

         for (int i = 0; i < list.size(); i++)
         {
            limitSum += list.get(i).getTime();
            // System.out.println(limitSum);
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

// ------- main --------------
// public static void main(String[] args) throws Exception
// {
// int target = 72;
// ArrayList<Integer> dataSet = new ArrayList<Integer>();
// // ArrayList<Sublist> choices = new ArrayList<Sublist>();
//
// int max = 0, kBest = 0;
// // boolean foundPerfect;
//
// dataSet.add(2);
// dataSet.add(12);
// dataSet.add(22);
// dataSet.add(5);
// dataSet.add(15);
// dataSet.add(25);
// dataSet.add(9);
// dataSet.add(19);
// dataSet.add(29);
//
// ArrayList<Sublist> powerset = makePowerset(dataSet);
//
// // for (int i = 0; i < powerset.size(); i++)
// // {
// // powerset.get(i).showSublist();
// // }
//
// for (int i = 0; i < powerset.size(); i++)
// {
// Sublist set = powerset.get(i);
// int sum = set.getSum();
// if (sum == target)
// {
// kBest = i;
// System.out.println("hi there");
// break;
// } else
// {
// if (sum > max && sum <= target)
// {
// max = sum;
// kBest = i;
// }
// }
// }
//
// System.out.print("Best set is: ");
// powerset.get(kBest).showSublist();
// System.out.print(" with sum of " + powerset.get(kBest).getSum());
// }
// }
