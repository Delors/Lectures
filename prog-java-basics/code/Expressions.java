void main() {
    String s = IO.readln("Enter your age: ");
    int age = Integer.parseInt(s);

    if (age >= 18) {
        IO.println("You are an adult.");
    } else {
        IO.println("You are a minor.");
    }

    var yearsUntil100 = 100-age;
    IO.println("You will be 100 in " + yearsUntil100 + " years.");
}