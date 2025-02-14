
// Question "Notify oder NotifyAll"?
import static java.lang.System.out;

public class ThreadsafeArray {

    private final Object[] array;

    public ThreadsafeArray(int size) {
        this.array = new Object[size];
    }

    public synchronized Object get(int index) throws InterruptedException {
        var v = array[index];
        while (v == null) {
            out.println(Thread.currentThread().getName() + " will go to sleep");
            wait();
            v = array[index];
        }
        return v;
    }

    public synchronized void set(int index, Object value) throws InterruptedException {
        while (array[index] != null) {
            out.println(Thread.currentThread().getName() + " will go to sleep");
            wait();
        }
        array[index] = value;
        notifyAll();
    }

    public synchronized void delete(int index) throws InterruptedException {
        while (array[index] == null) {
            out.println(Thread.currentThread().getName() + " will go to sleep");
            wait();
        }
        array[index] = null;
        notifyAll();
    }

    public static void main(String[] args) throws Exception {
        final var ARRAY_SIZE = 2;
        final var SLEEP_TIME = 1; // ms
        var array = new ThreadsafeArray(ARRAY_SIZE);
        for (int i = 0; i < ARRAY_SIZE; i++) {
            final var threadId = i;

            final var readerThreadName = "Reader";
            var t2 = new Thread(() -> {
                while (true) {
                    int j = (int) (Math.random() * ARRAY_SIZE);
                    try {
                        out.println(readerThreadName + "[" + j + "]" );
                        var o = array.get(j);
                        out.println(readerThreadName + "[" + j + "] ⇒ #" + o.hashCode());
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, readerThreadName);
            t2.start();

            // One Thread for each slot that will eventually write some content
            final var writerThreadName = "Writer[" + threadId + "]";
            var t1 = new Thread(() -> {
                while (true) {
                    try {
                        var o = new Object();
                        out.println(writerThreadName + " = #" + o.hashCode());
                        array.set(threadId, o);
                        out.println(writerThreadName + " done");
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, writerThreadName);
            t1.start();

            // One Thread for each slot that will eventually delete the content
            final var deleterThreadName = "Delete[" + threadId + "]";
            var t3 = new Thread(() -> {
                while (true) {
                    try {
                        out.println(deleterThreadName);
                        array.delete(threadId);
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, deleterThreadName);
            t3.start();
        }
    }
}
