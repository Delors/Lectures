import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VirtualBuffer {

    private final Random random = new Random();

    private Thread runDelayed(int id, Runnable task) {
        Thread thread = Thread.ofVirtual().start(
                () -> {
                    try {
                        // sleep 100ms (mean) + standard deviation of 20ms
                        var sleepTime =  (long) random.nextGaussian(100,20) ;
                        if (sleepTime < 0 ) {
                            // we found a gremlin...
                            return;
                        }
                        System.out.println("delaying " + id + " by " + sleepTime + "ms");
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    task.run();
                });
        return thread;
        // The change from native threads to virtual threads will increase
        // the performance tremendously; the program will finish in less 
        // than ~50% of the previous time.
        // (MacBook Pro M2 Max - 2023) 7secs. for 100 000 vs. 3secs.
        // with massive logging.
    }

    public static void main(String[] args) throws Exception {
        var start = System.nanoTime();
        VirtualBuffer buffer = new VirtualBuffer();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            final var no = i;
            var thread = buffer.runDelayed(
                i, 
                () -> System.out.println("i'm no.: " + no));
            threads.add(thread);
        }
        System.out.println("finished starting all threads");
        for (Thread thread : threads) {
            thread.join();
        }
        var runtime = (System.nanoTime() - start)/1_000_000;
        System.out.println(
            "all threads finished after: " + runtime + "ms"
        );
    }

}
