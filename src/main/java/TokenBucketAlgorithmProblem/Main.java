package TokenBucketAlgorithmProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This will be used to Test the Token Bucket Algo
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
       TokenBucketAlgorithm_2 tokenBucketAlgorithm = new TokenBucketAlgorithm_2(5);
       Set<Thread> threadSet = new HashSet<>();
       List<Thread> threadList = new ArrayList<>();

       for (int i = 0; i < 10; i++) {
           Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                   tokenBucketAlgorithm.getToken();
               }
           });
           thread.setName("Thread_"+i);
           threadSet.add(thread);
           threadList.add(thread);
       }

//       for (Thread t: threadSet) {
//           t.start();
//       }

        for (Thread t: threadList) {
            t.start();
        }

       for(Thread t: threadSet) {
           t.join();
       }

    }
}
