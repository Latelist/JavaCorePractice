package cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable {
    private CyclicBarrier barrier;

    public ComplexTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }


    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": Я занят.");
            Thread.sleep((long) (Math.random() * 3000));
            System.out.println(Thread.currentThread().getName() + ": Я закончил. Жду остальных.");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
