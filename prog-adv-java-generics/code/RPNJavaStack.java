import java.util.Stack;

public class RPN2 {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java RPN <expr>");
            return;
        }
        // Main logic
        Stack<Double> stack = new Stack<>();
        for (String arg : args) {
            switch (arg) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(Double.parseDouble(arg));
            }
        }
        System.out.println(stack.pop());
    }
}
