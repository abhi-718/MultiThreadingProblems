package UnisexBathRoomProblem;

public class UnisexBathRoomLockService {

    public int countOfMen;
    public int countOfWomen;

    public String isUseBy = "NONE";

//    private final ReentrantLock reentrantLock = new ReentrantLock();
    public UnisexBathRoomLockService() {
        countOfMen = 0;
        countOfWomen = 0;
    }

    public synchronized void acquireLockForMen() throws InterruptedException {
        while(countOfWomen > 0 || countOfMen >= 3) {
            wait();
        }

        countOfMen++;
        isUseBy = "MALE";
    }

    public synchronized void releaseLockForMen() {
        countOfMen--;
        notifyAll();
    }

    public synchronized void acquireLockForWomen() throws InterruptedException {
        while (countOfMen > 0 || countOfWomen >= 3) {
            wait();
        }
        countOfWomen++;
        isUseBy = "FEMALE";
    }

    public synchronized void releaseLockForWomen() {
        countOfWomen--;
        notifyAll();
    }


}
