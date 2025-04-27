package factorial;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private Long number;

    public FactorialTask(Long number) {
        this.number = number;
    }

    @Override
    protected Long compute() {
        if (number == 1) {
            return 1L;
        }

        FactorialTask subtask = new FactorialTask(number - 1);
        subtask.fork();
        Long subResult = subtask.join();

        return number * subResult;
    }
}
