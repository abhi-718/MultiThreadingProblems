package BarrierProblem;

public class BarrierProblemService {

    public static void main(String[] args) throws InterruptedException {
        final Barrier barrier = new Barrier(3);

        Thread p1 = new Thread(() -> {
           try {
               System.out.println("Thread 1");
               barrier.await1();
               System.out.println("Thread 1");
               barrier.await1();
               System.out.println("Thread 1");
               barrier.await1();
           }catch (InterruptedException ex) {

           }
        });

        Thread p2 = new Thread(() -> {
           try {
               Thread.sleep(500);
               System.out.println("Thread 2");
               barrier.await1();
               Thread.sleep(500);
               System.out.println("Thread 2");
               barrier.await1();
               Thread.sleep(500);
               System.out.println("Thread 2");
               barrier.await1();

           } catch (InterruptedException ex) {

           }
        });

        Thread p3 = new Thread(() -> {
           try {
               Thread.sleep(1000);
               System.out.println("Thread 3");
               barrier.await1();
               Thread.sleep(1000);
               System.out.println("Thread 3");
               barrier.await1();
               Thread.sleep(1000);
               System.out.println("Thread 3");
               barrier.await1();
           } catch (InterruptedException ex){

           }
        });

        p1.start();
        p2.start();
        p3.start();

        p1.join();
        p2.join();
        p3.join();
    }


}
