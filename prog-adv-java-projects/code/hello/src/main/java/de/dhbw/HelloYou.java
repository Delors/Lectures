package de.dhbw;

/**
 * Implements the main method to greet a user by name.
 */
public class HelloYou {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java HelloYou <name>");
            return;
        }
        System.out.println("Hello " + args[0] + "!");
}   }
