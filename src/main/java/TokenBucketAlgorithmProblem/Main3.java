package TokenBucketAlgorithmProblem;

import java.util.HashSet;
import java.util.Set;

public class Main3 {
    public static void main(String[] args) throws InterruptedException {

        TokenBucketFilter tbf = TokenBucketAlgoFactory.makeTokenBucketFilter(5);
        Set<Thread> threadSet = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    tbf.getToken();
                }
            });
            thread.setName("Thread_"+i);
            threadSet.add(thread);
        }

        for (Thread thread: threadSet) {
            thread.start();
        }

        for (Thread thread: threadSet) {
            thread.join();
        }
    }
}
