package TokenBucketAlgorithmProblem;

/**
 * Here we will be using one daemon thread which is going to run on the background and add the Tokens every seconds
 */


public class TokenBucketAlgo {

    private final int MAX_TOKENS;
    private int possibleTokens = 0;

    public TokenBucketAlgo(int maxTokens) {
        MAX_TOKENS = maxTokens;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                daemonThread();
            }
        });
        t1.setDaemon(true);
        t1.start();
    }


    public void daemonThread() {
        System.out.println("I am started");
        while (true) {
            synchronized (this) {
                if (possibleTokens < MAX_TOKENS) {
                    possibleTokens++;
                }
                this.notify();

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }


    public void getToken() {

        synchronized (this) {
            if (possibleTokens == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            possibleTokens--;
            System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (System.currentTimeMillis() / 1000));

        }

    }
}
