package ReadWriteLockProblem;


public class ReadWriteLockProblem {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        /**
         * Creating Multiple Threads to read the Values
         */

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                    sharedResource.readValue();
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Reader "+i).start();
        }

        new Thread(() -> {
            int value = 1;
           while(true) {
               sharedResource.writeValue(value++);
               try {
                   Thread.sleep(500);
               } catch (InterruptedException ex) {
                   Thread.currentThread().interrupt();
               }
           }
        }, "Writer ").start();


        ReadWriteLockCustom readWriteLockCustom = new ReadWriteLockCustom();



    }
}



