package TokenBucketAlgorithmProblem;

/**
 *  This class will implement Token Bucket Algorithm without the use of Daemon Threads.
 */


public class TokenBucketAlgorithm {
    private final int MAX_TOKENS;
    private long availableTokens = 0;
    private long lastRequestTime = System.currentTimeMillis();

    public TokenBucketAlgorithm(int maxTokens) {
        MAX_TOKENS = maxTokens;
    }

    synchronized void getToken() throws InterruptedException {

        availableTokens += (System.currentTimeMillis() - lastRequestTime)/1000;

        if (availableTokens >= MAX_TOKENS) {
            availableTokens = MAX_TOKENS;
        }

        if (availableTokens == 0) {
            Thread.sleep(1000);
        } else {
            availableTokens--;
        }
        lastRequestTime = System.currentTimeMillis();
        System.out.printf("Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));
        System.out.println();

    }
}
