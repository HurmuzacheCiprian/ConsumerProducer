package com.ciprianconstantin.consumer;

import com.ciprianconstantin.events.Event;
import com.ciprianconstantin.queue.Queue;

/**
 * Created by churmuzache on 7/27/15.
 */
public class Consumer<E extends Event> implements Runnable {

    private Queue<E> queue;

    public Consumer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Queue size: "+queue.length());
        System.out.println("Consumer " + Thread.currentThread().getId() + " initiated...");
        while(true) {
            synchronized (queue) {
                int queueLength = queue.length();
                while(queue.isEmpty()) {
                    try {
                        System.out.println("Consumer " + Thread.currentThread().getId() + " is waiting for event...");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Consumer " + Thread.currentThread().getId() + " is consuming event from the queue");
                E event = queue.poll();
                System.out.println("Consumed event " + event);
                queue.notifyAll();

            }
        }
    }

}
