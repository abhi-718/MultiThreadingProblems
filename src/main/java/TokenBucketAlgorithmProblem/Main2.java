package TokenBucketAlgorithmProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        TokenBucketAlgo tokenBucketAlgo = new TokenBucketAlgo(5);

        Set<Thread> threadSet = new HashSet<>();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    tokenBucketAlgo.getToken();
                }
            });
            t1.setName("Thread_"+i);
            threadSet.add(t1);
            threadList.add(t1);
        }

        for (Thread thread: threadSet) {
            thread.start();
        }

        for (Thread thread: threadSet) {
            thread.join();
        }

    }
}
