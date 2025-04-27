package factorial;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        Long n = 12L;

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        Long result = forkJoinPool.invoke(factorialTask);

        System.out.println(result);
    }
}
