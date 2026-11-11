import java.util.*;
public class ComparatorValidation {
 record Score(String name,int points,int difference){}
 public static void main(String[] args){
  Comparator<Score> order=Comparator.comparingInt(Score::points).reversed().thenComparing(Comparator.comparingInt(Score::difference).reversed()).thenComparing(Score::name);
  List<Score> scores=List.of(new Score("A",Integer.MAX_VALUE,5),new Score("B",Integer.MIN_VALUE,5),new Score("C",9,6),new Score("D",9,4),new Score("E",9,4),new Score("D",9,4));
  int checks=0;for(Score a:scores)for(Score b:scores){Bench.require(Integer.signum(order.compare(a,b))==-Integer.signum(order.compare(b,a)),"antisymmetry "+checks++);for(Score c:scores)if(order.compare(a,b)<=0&&order.compare(b,c)<=0&&order.compare(a,c)>0)throw new AssertionError("transitivity");}
  Comparator<Score> broken=(a,b)->b.points()-a.points();
  System.out.println("Overflow comparator says MAX before MIN="+(broken.compare(scores.get(0),scores.get(1))<0));
  Bench.require(order.compare(scores.get(0),scores.get(1))<0,"Integer.compare avoids subtraction overflow");
  List<Score> original=new ArrayList<>(scores),copy=new ArrayList<>(scores);copy.sort(order);
  Bench.require(original.equals(scores),"ranking copy preserves insertion list");
 }
}
