package calculator;

public class Calc {
    private String[] tokens;
    private int pos; // token position
    public Calc(String expr) {
        this.tokens = expr.split(" ");
        this.pos = 0;
    }

    public static void main(String[] args) {
        String expr = "1 + 2 * 3 / ( 2 * 2 - 5 )";

        Calc calc = new Calc(expr);
        double result = calc.parse();

        System.out.println(expr + " = " + result);

    }
    private double parse() { // проверка наличия сложения и вычитания
        double first = multiply();
        while (pos < tokens.length) {
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
    private double multiply() { // проверка наличия умножения и деления
        double first = factor();
        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else  { pos++; }

            double second = factor();
            if (operator.equals("*")) {
                first *= second;
            } else { first /= second; }
        }

        return first;
    }
    private double factor() { // проверка наличия скобок
        String next = tokens[pos];
        double result;
        if (next.equals("(")) {
            pos++;
            result = parse();
            String closingBracket;
            if (pos < tokens.length) {
                closingBracket = tokens[pos];
            } else {
                throw new IllegalArgumentException("Unexpected end of expression");
            }
            if (closingBracket.equals(")")) {
                pos++;
                return result;
            }
            throw new IllegalArgumentException("')' expected but " + closingBracket + " found");
        }
        pos++;
        return Double.parseDouble(next);
    }
}
