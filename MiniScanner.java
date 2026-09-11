public class MiniScanner {
    public static void main(String[] args) {
        String source = "+-;";
        int current = 0;

        while (current < source.length()) {
            char c = source.charAt(current);

            if (c == '+') {
                System.out.println("Found PLUS");
            } else if (c == '-') {
                System.out.println("Found MINUS");
            } else if (c == ';') {
                System.out.println("Found SEMICOLON");
            }

            current = current + 1;
        }
    }
}