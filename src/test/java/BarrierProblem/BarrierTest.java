package BarrierProblem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BarrierTest {
    private Barrier barrier;
    private final int TOTAL_THREADS = 5;

    @BeforeEach
    public void setUp() {
        barrier = new Barrier(TOTAL_THREADS);
    }

    @Test
    public void testBarrier() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(TOTAL_THREADS);
        for (int i = 0; i < TOTAL_THREADS; i++) {
            executor.execute(new Worker(barrier));
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(5, TimeUnit.SECONDS);
        assertTrue(finished, "All threads did not finish in time");
    }

    private static class Worker implements Runnable {
        private final Barrier barrier;

        public Worker(Barrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await1();
                // Perform some work after the barrier
                System.out.println(Thread.currentThread().getName() + " is working after the barrier");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
