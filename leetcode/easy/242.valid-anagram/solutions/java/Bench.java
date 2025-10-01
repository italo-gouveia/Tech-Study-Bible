import java.util.Arrays;

public class Bench {
    static String S = "anagram".repeat(1000);
    static String T = "nagaram".repeat(1000);

    static boolean count(String s, String t){
        if (s.length()!=t.length()) return false;
        int[] c = new int[26];
        for (int i=0;i<s.length();i++){
            c[s.charAt(i)-'a']++;
            c[t.charAt(i)-'a']--;
        }
        for (int x: c) if (x!=0) return false;
        return true;
    }
    static boolean sort(String s, String t){
        if (s.length()!=t.length()) return false;
        char[] a=s.toCharArray(), b=t.toCharArray();
        Arrays.sort(a); Arrays.sort(b);
        return Arrays.equals(a,b);
    }
    public static void main(String[] args) {
        long t0=System.nanoTime();
        for(int i=0;i<1000;i++) count(S,T);
        long t1=System.nanoTime();
        for(int i=0;i<200;i++) sort(S,T);
        long t2=System.nanoTime();
        System.out.printf("count: %d ms, sort: %d ms%n", (t1-t0)/1_000_000, (t2-t1)/1_000_000);
    }
}
