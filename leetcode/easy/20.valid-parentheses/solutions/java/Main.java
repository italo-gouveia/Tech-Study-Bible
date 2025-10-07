public class Main {
    public static void main(String[] args) {
        System.out.println(solution1.isValid("()"));      // true
        System.out.println(solution1.isValid("()[]{}"));  // true
        System.out.println(solution1.isValid("(]"));      // false
        System.out.println(solution1.isValid("([])"));    // true
        System.out.println(solution1.isValid("([)]"));    // false
    }
}



