import java.util.*;
public class TicketText {
 static String build(String[] fields,int mode){
  if(mode==0){String text="";for(String f:fields)text+=f;return text;}
  if(mode==1){StringBuilder text=new StringBuilder();for(String f:fields)text.append(f);return text.toString();}
  StringBuffer text=new StringBuffer();for(String f:fields)text.append(f);return text.toString();
 }
 public static void main(String[] args){
  int[][] loads={{1,5},{10000,50},{100,500}};
  for(int[] load:loads){
   String[] fields=new String[load[1]];Arrays.setAll(fields,i->"field"+i+"=value;");
   String expected=String.join("",fields);
   for(int mode=0;mode<3;mode++){
    final int m=mode;Bench.require(build(fields,m).equals(expected),"same ticket content mode "+m+" fields "+fields.length);
    Bench.measure(new String[]{"String_plus","StringBuilder","StringBuffer"}[m],load[0]*load[1],()->{long total=0;for(int t=0;t<load[0];t++)total+=build(fields,m).length();Bench.sink=total;});
   }
   System.out.println("WORKLOAD tickets="+load[0]+" fields="+load[1]+" estimated loop String results="+load[0]*load[1]);
  }
  StringBuffer log=new StringBuffer();Thread[] workers=new Thread[4];
  for(int i=0;i<workers.length;i++)workers[i]=new Thread(()->{for(int n=0;n<100;n++)synchronized(log){log.append("booking|").append(n).append('\n');}});
  for(Thread t:workers)t.start();for(Thread t:workers)try{t.join();}catch(InterruptedException e){Thread.currentThread().interrupt();throw new RuntimeException(e);}
  Bench.require(log.toString().lines().count()==400,"shared log retains complete records under compound-operation lock");
 }
}
