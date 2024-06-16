package ReadWriteLockProblem;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Read Write Lock
 */
public class ReadWriteLockCustom {

    private boolean isWriteLocked;
    private int reader;

    public ReadWriteLockCustom() {
        isWriteLocked = false;
        reader = 0;
    }
    public synchronized void acquireReadLock() throws InterruptedException {

        while(isWriteLocked) {
            wait();
        }

        reader++;
    }

    public synchronized void releaseReadLock() {
        reader--;
        notify();
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
        while(isWriteLocked || reader != 0) {
            wait();
        }
        isWriteLocked = true;

    }

    public synchronized void releaseWriteLock() {
        isWriteLocked = false;
        notify();
    }
}


