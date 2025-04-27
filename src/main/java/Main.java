import myBlockingQueue.MyBlockingQueue;

public class Main {
    public static void main(String[] args) {

        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>();

        Thread producer = new Thread(() ->{
            try {
                for (int i = 0; i < 10; i++){
                    queue.enqueue(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() ->{
            try{
                for (int i = 0; i < 10; i++) {
                    Integer last = queue.dequeue();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}
