package part2;

import java.util.HashSet;
import java.util.Objects;

public class Driver {
    private static int checks;
    private static void check(boolean ok, String label) {
        if (!ok) throw new AssertionError(label);
        checks++;
        System.out.println("PASS: " + label);
    }
    private static void invalid(Runnable call, String label) {
        try { call.run(); } catch (IllegalArgumentException expected) {
            check(true,label); return;
        }
        throw new AssertionError(label);
    }
    private static void show(LinearProbingHashST<Integer,String> t) {
        System.out.println("size="+t.size()+", capacity="+t.capacity());
        for (int i=0; i<t.capacity(); i++) {
            Integer k=t.keyAt(i);
            System.out.println("  slot " + i + ": " + (k==null ? "empty" : k+"="+t.get(k)));
        }
    }
    public static void main(String[] args) {
        System.out.println("=== LinearProbingHashST ===");
        LinearProbingHashST<Integer,String> t=new LinearProbingHashST<>(8);
        check(t.isEmpty() && t.size()==0,"new table is empty");
        check(t.get(99)==null && !t.contains(99),"missing key");
        for (int k : new int[]{7,15,23}) {
            System.out.println("INSERT "+k+" -> home bucket "+t.bucketIndex(k));
            t.put(k,"v"+k); show(t);
        }
        check(t.size()==3 && "v23".equals(t.get(23)),"colliding keys remain retrievable");
        check(t.keyAt(7)==7 && t.keyAt(0)==15 && t.keyAt(1)==23,"linear probing wraps from slot 7 to slots 0 and 1");
        t.put(15,"updated");
        check(t.size()==3 && "updated".equals(t.get(15)),"duplicate key updates value without adding a key");
        t.delete(15); show(t);
        check(t.get(15)==null && "v23".equals(t.get(23)) && "v7".equals(t.get(7)),"delete middle collision; other keys survive");
        t.delete(999); check(t.size()==2,"delete missing key is harmless");
        t.put(7,null); check(!t.contains(7) && t.size()==1,"null value deletes key");
        t.delete(23); check(t.isEmpty(),"delete final key");
        t.put(-1,"negative"); t.put(Integer.MIN_VALUE,"minimum");
        check("negative".equals(t.get(-1)) && "minimum".equals(t.get(Integer.MIN_VALUE)),"negative hash codes including MIN_VALUE");
        invalid(()->t.put(null,"x"),"put rejects null key");
        invalid(()->t.get(null),"get rejects null key");
        invalid(()->t.delete(null),"delete rejects null key");
        invalid(()->t.contains(null),"contains rejects null key");
        LinearProbingHashST<String,Integer> strings=new LinearProbingHashST<>();
        strings.put(new String("Aa"),1); strings.put("BB",2);
        check("Aa".hashCode()=="BB".hashCode() && strings.get(new String("Aa"))==1 && strings.get("BB")==2,"distinct strings with identical hashCode; equality by equals");
        LinearProbingHashST<Integer,Integer> big=new LinearProbingHashST<>(4);
        for(int i=0;i<100;i++) big.put(i,i*i);
        check(big.capacity()>4,"growth rehash: capacity="+big.capacity());
        boolean found=true;
        for(int i=0;i<100;i++) found &= Objects.equals(big.get(i),i*i);
        check(found,"all 100 values survive growth");
        int grown=big.capacity();
        for(int i=0;i<98;i++) big.delete(i);
        check(big.capacity()<grown && big.get(98)==9604 && big.get(99)==9801,"shrink rehash preserves remaining values");
        HashSet<Integer> keys=new HashSet<>(); for(Integer k:big.keys()) keys.add(k);
        check(keys.size()==2 && keys.contains(98) && keys.contains(99),"keys iteration after deletion");
        System.out.println("ALL "+checks+" CHECKS PASSED");
    }
}
