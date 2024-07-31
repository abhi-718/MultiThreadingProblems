package TokenBucketAlgorithmProblem;

public final class TokenBucketAlgoFactory {

    public TokenBucketAlgoFactory() {

    }

    public static TokenBucketFilter makeTokenBucketFilter(int maxTokens) {
            MultiThreadingTokenBucketAlgo multiThreadingTokenBucketAlgo = new MultiThreadingTokenBucketAlgo(maxTokens);
            multiThreadingTokenBucketAlgo.intialize();
            return multiThreadingTokenBucketAlgo;
    }

    private static class MultiThreadingTokenBucketAlgo  extends TokenBucketFilter{

        private final int MAX_TOKENS;
        private int possibleTokens;


        private MultiThreadingTokenBucketAlgo(int maxTokens) {
            MAX_TOKENS = maxTokens;
        }


        public void intialize() {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    daemonThread();
                }
            });
            t1.setDaemon(true);
            t1.start();
        }

        private void daemonThread() {

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


        @Override
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
}
