import java.util.*;
public class EqualityInvestigation {
 static class Broken {
  String reg;Broken(String reg){this.reg=reg;}
  @Override public boolean equals(Object o){return o instanceof Broken b&&reg.equals(b.reg);}
 }
 static class Mutable {
  String reg;Mutable(String reg){this.reg=reg;}
  @Override public boolean equals(Object o){return o instanceof Mutable m&&reg.equals(m.reg);}
  @Override public int hashCode(){return reg.hashCode();}
 }
 public static void main(String[] args){
  Broken same=new Broken("A");Set<Broken> apparent=new HashSet<>();apparent.add(same);apparent.add(same);
  System.out.println("Same reference predicted=1 observed="+apparent.size());
  Set<Broken> exposed=new HashSet<>();exposed.add(new Broken("A"));exposed.add(new Broken("A"));
  System.out.println("Distinct equal instances identity hashes: observed="+exposed.size());
  Mutable changed=new Mutable("A");Set<Mutable> mutable=new HashSet<>();mutable.add(changed);changed.reg="B";
  System.out.println("After mutation contains changed object="+mutable.contains(changed));
  Set<Student> corrected=new HashSet<>(List.of(new Student("A","Arun"),new Student("A","Arun")));
  Bench.require(apparent.size()==1&&corrected.size()==1,"identity test and corrected equality test");
  System.out.println("Without hashCode, Identity-hash collisions are possible; duplicate exposure is an observation, not a mathematical guarantee");
 }
}
