import java.util.*;
public class QueueInvestigation {
 record Case(String name,int severity,long arrival,long waited){}
 static Comparator<Case> normal=Comparator.comparingInt(Case::severity).thenComparingLong(Case::arrival);
 public static void main(String[] args){
  var patients=List.of(new Case("Ravi",3,0,0),new Case("Meena",1,1,0),new Case("John",3,2,0),new Case("Asha",5,3,120),new Case("Kumar",1,4,0),new Case("Lata",2,5,0),new Case("Bavi",2,6,0),new Case("Thanu",4,7,0));
  var q=new PriorityQueue<Case>(normal);q.addAll(patients);StringJoiner names=new StringJoiner(" ");while(!q.isEmpty())names.add(q.poll().name());
  Bench.require(names.toString().equals("Meena Kumar Lata Bavi Ravi John Thanu Asha"),"eight-patient hand verified order");
  System.out.println("Order: "+names);
  Comparator<Case> aged=Comparator.comparingInt((Case c)->c.severity()==1?1:Math.max(2,c.severity()-(int)(c.waited()/30))).thenComparingLong(Case::arrival);
  var refreshed=new PriorityQueue<Case>(aged);refreshed.addAll(patients);System.out.print("Snapshot aging order: ");while(!refreshed.isEmpty())System.out.print(refreshed.poll().name()+" ");System.out.println();
  for(int n:new int[]{100,5000,50000})Bench.measure("priority_admit_and_serve",n,()->{var heap=new PriorityQueue<Case>(normal);for(int i=0;i<n;i++)heap.add(new Case("P"+i,i%5+1,i,0));long sum=0;while(!heap.isEmpty())sum+=heap.poll().arrival();Bench.sink=sum;});
  System.out.println("Aging uses an immutable time snapshot and rebuild; never a comparator that changes inside a live heap");
  System.out.println("Continuous critical arrivals still preclude a finite waiting guarantee for noncritical cases");
 }
}
