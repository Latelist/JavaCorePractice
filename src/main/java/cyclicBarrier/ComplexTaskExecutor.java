package cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ComplexTaskExecutor {
    private final int numberOfTasks;
    private ExecutorService service;
    private CyclicBarrier barrier;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void executeTasks() {
        System.out.println("Экзекутор: Начинаю выполнять задачи в потоке " + Thread.currentThread().getName() + ". ");
        barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("Экзекутор: Все задачи в потоке " + Thread.currentThread().getName() + " завершены.");
        });

        service = Executors.newFixedThreadPool(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            service.submit(new ComplexTask(barrier));
        }
        try {
            service.shutdown();
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
