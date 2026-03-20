int counter = 0; // or long, or float, or ...

void main() throws InterruptedException {
    Runnable increment = () -> {
        for (int i = 0; i < 100_000; i++)
           counter++;
    };

    var t1 = new Thread(increment);
    var t2 = new Thread(increment);
    t1.start();     t2.start();
    t1.join();      t2.join();

    IO.println("Counter: " + counter);
}
