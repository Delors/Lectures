// Question "Notify or NotifyAll"?
import static java.lang.System.out;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsafeArrayWithConditionVariables {

    private final Object[] array;
    private final ReentrantLock[] locks;
    private final Condition[] notEmptyConditions;
    private final Condition[] notFullConditions;

    public ThreadsafeArrayWithConditionVariables(int size) {
        this.array = new Object[size];
        this.locks = new ReentrantLock[size];
        this.notEmptyConditions = new Condition[size];
        this.notFullConditions = new Condition[size];
        for (int i = 0; i < size; i++) {
            locks[i] = new ReentrantLock(true);
            notEmptyConditions[i] = locks[i].newCondition(); 
            notFullConditions[i] = locks[i].newCondition();
        }
    }

    public Object get(int index) throws InterruptedException {
        locks[index].lock();
        try {
            var v = array[index];
            while (v == null) {
                out.println(Thread.currentThread().getName() + " will go to sleep");
                notEmptyConditions[index].await();
                out.println(Thread.currentThread().getName() + " awakened");
                v = array[index];
            }
            return v;
        } finally {
            locks[index].unlock();
        }
    }

    public void set(int index, Object value) throws InterruptedException {
        locks[index].lock();
        try {
            while (array[index] != null) {
                out.println(Thread.currentThread().getName() + " will go to sleep");
                notFullConditions[index].await();
                out.println(Thread.currentThread().getName() + " awakened");
            }
            array[index] = value;
            notEmptyConditions[index].signalAll(); // otherwise, it may happen that we "just" wake up a getter thread...
        } finally {
            locks[index].unlock();
        }
    }

    public void delete(int index) throws InterruptedException{
        locks[index].lock();
        try {
            while (array[index] == null) {
                out.println(Thread.currentThread().getName() + " will go to sleep");
                notEmptyConditions[index].await();
                out.println(Thread.currentThread().getName() + " awakened");
            }
            array[index] = null;
            notFullConditions[index].signal();
        } finally {
            locks[index].unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        final var ARRAY_SIZE = 2;
        final var SLEEP_TIME = 100; // ms
        var array = new ThreadsafeArrayWithConditionVariables(ARRAY_SIZE);
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
            t2.setPriority(Thread.MIN_PRIORITY);

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
            t3.setPriority(Thread.NORM_PRIORITY + 1);
            t3.start();
            t3.setPriority(Thread.NORM_PRIORITY + 1);
        }
    }
}
