package myBlockingQueue;

import java.util.*;
import java.util.stream.Stream;

public class MyBlockingQueue<T> {
    private final ArrayDeque<T> queue;
    private final int capacity = 4;

    public MyBlockingQueue(){
        this.queue = new ArrayDeque<>();
    }

    public synchronized void enqueue(T obj) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Очередь заполнена, ждём");
            wait();
        }
        queue.add(obj);
        System.out.println("В очередь добавлено: " + obj);
        printQueue();
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Очередь пуста, ждём");
            wait();
        }
        T last = queue.removeFirst();
        System.out.println("Из очереди извлечено: " + last);
        printQueue();
        notifyAll();
        return last;
    }
    public synchronized void printQueue() {
        queue.stream().forEach(e -> System.out.print(e + " "));
        System.out.println();
    }

}
