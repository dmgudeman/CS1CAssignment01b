import java.util.ArrayList;



//------------------------------------------------------
public class Foothill
{
   public static ArrayList<Sublist> powerset(ArrayList<Integer> list) throws CloneNotSupportedException {
      ArrayList<Sublist> ps = new ArrayList<Sublist>();
      ps.add(new Sublist(list));   // add the empty set
     
      // for every item in the original list
      for (int i = 0; i < list.size(); i++) {
         
         ArrayList<Sublist> newPs = new ArrayList<Sublist>();
     
        for (Sublist subset : ps) {
          // copy all of the current powerset's subsets
          newPs.add(subset);
     
          // plus the subsets appended with the current item
          Sublist newSubset = new Sublist(list);
          newSubset.getIndices().addAll(subset.getIndices());
          newSubset.addItem(i); //
//          newSubset.add(item);
          newPs.add(newSubset);
        }
     
        // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
        ps = newPs;
      }
      return ps;
    }
   
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int target = 72;
      ArrayList<Integer> dataSet = new ArrayList<Integer>();
      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      
      int k, j, numSets, max =
      0, kBest =
      0, masterSum;
      boolean foundPerfect;

      dataSet.add(2); dataSet.add(12); dataSet.add(22);
      dataSet.add(5); dataSet.add(15); dataSet.add(25);
      dataSet.add(9); dataSet.add(19); dataSet.add(29);

      ArrayList<Sublist> powerset = powerset(dataSet);
      
      for (int i = 0; i < powerset.size(); i++) {
         powerset.get(i).showSublist();
      }
      
      for (int i = 0; i < powerset.size(); i++) {
         Sublist set = powerset.get(i);
         int sum = set.getSum();
         if (sum == target) {
            kBest = i;
            break;
         } else {
            if (sum > max && sum <= target) {
               max = sum;
               kBest = i;
            }
         }
      }
      
      System.out.print("Best set is: ");
      powerset.get(kBest).showSublist();
      System.out.print(" with sum of " + powerset.get(kBest).getSum());
      
//      ArrayList<Integer> makeChoicesList() {
         
//         for(k=0; k < dataSet.size(); k++)
//         {
//            addItem.choices()
//         }
         
//      }
      
      

     // choices.get(kBest).showSublist();
      
//      System.out.println(choices.toString());
   }
}
