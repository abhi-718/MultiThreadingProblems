package TokenBucketAlgorithmProblem;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  By the Use of Reentrant Lock we ensured the fairness.
 */

public class TokenBucketAlgorithm_3 {

    private final int MAX_TOKENS;
    private long lastRequestTime = System.currentTimeMillis();
    private long possibleTokens = 0;
//    private int tokenPerSecond = 2;

    private final Lock lock = new ReentrantLock(true);


    public TokenBucketAlgorithm_3(int maxTokens) {
        MAX_TOKENS = maxTokens;
    }

    public void getToken() {
        lock.lock();
        try {
            possibleTokens += (((System.currentTimeMillis() - lastRequestTime)/1000) * 2);
            if (possibleTokens >= MAX_TOKENS) {
                possibleTokens = MAX_TOKENS;
            }
            if (possibleTokens == 0) {
                Thread.sleep(1000);
            } else {
                possibleTokens--;
            }
            lastRequestTime = System.currentTimeMillis();
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        } finally
        {
            lock.unlock();
        }
        System.out.printf("Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
        System.out.println();

    }
}
