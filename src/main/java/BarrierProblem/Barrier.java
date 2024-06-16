package BarrierProblem;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A barrier can be thought of as a point in the program code,which all or some of the threads need to reach at before any one of them is allowed to proceed further.
 */
public class Barrier {
    private int count;
    private final int totalThreads;
    private int released;

    private final ReentrantLock reentrantLock;
    private final Condition condition;
    public Barrier(int totalThreads) {
        this.totalThreads = totalThreads;
        reentrantLock = new ReentrantLock();
        this.condition = this.reentrantLock.newCondition();
    }

    public synchronized void await() throws InterruptedException{
        count++;

        if (count == totalThreads) {
            notifyAll();
            released = totalThreads;

        } else {
            while (count < totalThreads) {
                wait();
            }
        }
        System.out.println("This Thread completed its work");
        released--;
        if (released == 0) {
            count = 0;
            notifyAll();
        }

    }


    public void await1() throws InterruptedException {
        reentrantLock.lock();
        try {
            count++;
            if (count == totalThreads) {
                released = totalThreads;
                condition.signalAll();

            } else {
                while(count < totalThreads) {
                    condition.await();
                }
            }

            System.out.println("This Thread completed its work");
            released--;
            if (released == 0) {
                count = 0;
                condition.signalAll();
            }

        } finally {
            reentrantLock.unlock();
        }

    }
}
