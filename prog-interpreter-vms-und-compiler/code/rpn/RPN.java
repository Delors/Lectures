package rpn;

import ds.Stack;

public class RPN {

    static void printAll(Stack<String> stack) {
        for (int i = 0; i < stack.size(); i++) {
            IO.print(stack.get(i) + " ");
        }
        IO.println();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            IO.println("Usage: java RPN <expr>");
            return;
        }
        // Main logic
        Stack<String> infix = new Stack<>();
        Stack<Double> ops = new Stack<>();
        for (String arg : args) {
            switch (arg) {
                case "+":
                    ops.push(ops.pop() + ops.pop());
                    infix.push("(" + infix.pop() + " + " + infix.pop() + ")");
                    break;
                case "*":
                    ops.push(ops.pop() * ops.pop());
                    infix.push("(" + infix.pop() + " * " + infix.pop() + ")");
                    break;
                default:
                    infix.push(arg);
                    ops.push(Double.parseDouble(arg));
            }
        }
        IO.println(infix.peek() + " = " + ops.peek());

        printAll(infix);
    }
}
