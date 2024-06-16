package ReadWriteLockProblem;

import java.util.concurrent.locks.ReentrantReadWriteLock;



public class SharedResource {

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private int value = 0;

    public int readValue() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() +" is reading the value " +value +" Time is "+System.currentTimeMillis());
            return value;
        }finally {
            readLock.unlock();
        }
    }

    public void writeValue(int newValue) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "is writing the value "+value + "Time is "+System.currentTimeMillis());
            value = newValue;
        }finally {
            writeLock.unlock();
        }
    }

}
