public class solution1 {
    // Counting-free stack using char array
    public static boolean isValid(String s) {
        if (s.length() % 2 == 1) return false;
        char[] st = new char[s.length()];
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') st[++top] = c;
            else {
                if (top < 0) return false;
                char o = st[top--];
                if ((c == ')' && o != '(') || (c == ']' && o != '[') || (c == '}' && o != '{')) return false;
            }
        }
        return top == -1;
    }
}
