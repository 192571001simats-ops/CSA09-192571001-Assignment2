import java.util.*;
public class PharmacyMaps {
 record Medicine(String code,int stock,int sequence){}
 static String report(Map<String,Medicine> map,boolean codeOrder){
  List<Medicine> rows=new ArrayList<>(map.values());rows.sort(codeOrder?Comparator.comparing(Medicine::code):Comparator.comparingInt(Medicine::sequence));
  StringBuilder text=new StringBuilder();for(Medicine m:rows)text.append(m.code()).append(':').append(m.stock()).append('\n');return text.toString();
 }
 public static void main(String[] args){
  for(int n:new int[]{100,1000,10000}){
   List<Medicine> input=new ArrayList<>();for(int i=0;i<n;i++)input.add(new Medicine(String.format("M%06d",i),i%50,0));Collections.shuffle(input,new Random(19));
   List<Medicine> ordered=new ArrayList<>();for(int i=0;i<n;i++)ordered.add(new Medicine(input.get(i).code(),i%50,i));
   String refCode=null,refArrival=null;
   for(int kind=0;kind<3;kind++){
    Map<String,Medicine> map=switch(kind){case 0->new HashMap<>();case 1->new TreeMap<>();default->new LinkedHashMap<>();};
    for(Medicine m:ordered)map.put(m.code(),m);
    String name=map.getClass().getSimpleName();
    Bench.measure(name+"_100000_lookups",n,()->{long sum=0;for(int q=0;q<100000;q++)sum+=map.get(ordered.get(q%n).code()).stock();Bench.sink=sum;});
    Bench.measure(name+"_code_report",n,()->Bench.sink=report(map,true).length());
    Bench.measure(name+"_arrival_report",n,()->Bench.sink=report(map,false).length());
    if(refCode==null){refCode=report(map,true);refArrival=report(map,false);}else Bench.require(refCode.equals(report(map,true))&&refArrival.equals(report(map,false)),name+" both report orders identical");
   }
  }
  System.out.println("Acceptance target: average map lookup under 100 microseconds; report I/O is outside in-memory benchmark");
 }
}
