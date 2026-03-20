byte counter = 0; 

void main() throws InterruptedException {
    Runnable increment = () -> { 
        for (int i = 0; i < 60; i++) 
            counter++;
    };

    long runs = 0;
    do {    runs++;
            counter = 0;
            var t1 = new Thread(increment);     var t2 = new Thread(increment);
            t1.start();                         t2.start();
            t1.join();                          t2.join();
    } while (counter == 120);
    IO.println("Needed runs: " + runs);
}
