import ds.Stack;

public class RPN {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java RPN <expr>");
            return;
        }
        // Main logic
        Stack stack = new Stack();
        for (String arg : args) {
            switch (arg) {
                case "+":
                    stack.push((double) stack.pop() + (double) stack.pop());
                    break;
                case "*":
                    stack.push((double) stack.pop() * (double) stack.pop());
                    break;
                default:
                    stack.push(Double.parseDouble(arg));
            }
        }
        System.out.println(stack.pop());
    }
}
