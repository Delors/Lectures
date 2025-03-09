import java.util.Stack;

public class RPN {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java RPN <expr>");
            return;
        }
        // Main logic
        Stack<String> infix = new Stack<>();
        Stack<Double> ops = new Stack<>();
        for (String arg : args) {
            switch (arg) {
                case "+":
                case "*":
                case "/":
                    if (ops.size() < 2) {
                        System.out.println("Error: / requires two operands");
                        return;
                    }
                    var right = ops.pop();
                    var left = ops.pop();
                    var rightTerm = infix.pop();
                    var leftTerm = infix.pop();
                    switch (arg) {
                        case "+" -> ops.push(left + right);
                        case "*" -> ops.push(left * right);
                        case "/" -> ops.push(left / right);
                    }
                    infix.push("(" + leftTerm + " " + arg + " " + rightTerm + ")");
                    break;
                case "sqrt":
                    if (ops.size() < 1) {
                        System.out.println("Error: sqrt requires one operand");
                        return;
                    }
                    ops.push(Math.sqrt(ops.pop()));
                    infix.push("sqrt(" + infix.pop() + ")");
                    break;
                default:
                    infix.push(arg);
                    ops.push(Double.parseDouble(arg));
            }
        }
        System.out.println(infix.pop() + " = " + ops.pop());

        if (infix.size() > 0) {
            System.out.println("Unused: ");
            infix.forEach(System.out::println);
        }
    }
}