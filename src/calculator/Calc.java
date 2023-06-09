package calculator;

public class Calc {
    private String[] tokens;
    private int pos; // token position
    public Calc(String expr) {
        this.tokens = expr.split(" ");
        this.pos = 0;
    }

    public static void main(String[] args) {
        String expr = "1 + 2 * 3 / 2 - 5";

        Calc calc = new Calc(expr);
        double result = calc.parse();

        System.out.println(expr + " = " + result);

    }
    private double parse() {
        double first = multiply();
        while (pos < tokens.length-1) {
            String operator = tokens[pos];
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else  { pos++; }

            double second = multiply();
            if (operator.equals("+")) {
                first += second;
            } else { first -= second; }
        }

        return first;
    }
    private double multiply() {
        double first = Double.parseDouble(tokens[pos++]);
        while (pos < tokens.length-1) {
            String operator = tokens[pos];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else  { pos++; }

            double second = Double.parseDouble(tokens[pos++]);
            if (operator.equals("*")) {
                first *= second;
            } else { first /= second; }
        }

        return first;
    }
}
