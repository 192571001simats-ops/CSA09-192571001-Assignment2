import java.util.*;
public class Playlists {
 static void run(int n,boolean linked,String operation){
  List<Integer> list=linked?new LinkedList<>():new ArrayList<>(n+200);
  for(int i=0;i<n;i++)list.add(i);long sum=0;
  switch(operation){
   case "append":for(int i=0;i<200;i++)list.add(i);break;
   case "front":for(int i=0;i<200;i++){list.add(0,i);sum+=list.remove(0);}break;
   case "middle_index":for(int i=0;i<200;i++){list.add(n/2,i);sum+=list.remove(n/2);}break;
   case "middle_iterator":ListIterator<Integer> it=list.listIterator(n/2);for(int i=0;i<200;i++){it.add(i);it.previous();it.remove();}break;
   case "get":for(int i=0;i<1000;i++)sum+=list.get((i*37)%n);break;
   case "iterate":for(int value:list)sum+=value;break;
  }
  Bench.sink=sum+list.size();
 }
 public static void main(String[] args){
  for(int n:new int[]{100,1000,10000})for(boolean linked:new boolean[]{false,true})for(String op:List.of("append","front","middle_index","middle_iterator","get","iterate")){
   Bench.measure((linked?"LinkedList_":"ArrayList_")+op,n,()->run(n,linked,op));
  }
  for(int n:new int[]{100,1000,10000})Bench.measure("ArrayDeque_two_ends",n,()->{Deque<Integer> q=new ArrayDeque<>();for(int i=0;i<n;i++){q.addFirst(i);q.addLast(i);}long sum=0;while(!q.isEmpty())sum+=q.removeFirst();Bench.sink=sum;});
  Deque<String> next=new ArrayDeque<>();next.addLast("Song A");next.addFirst("Song B");
  Bench.require(next.removeFirst().equals("Song B"),"Up Next is a deque");
  System.out.println("List timings include identical population cost; iterator case separates traversal from repeated edits conceptually");
 }
}
