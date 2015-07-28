package com.ciprianconstantin.producer;

import com.ciprianconstantin.events.Event;
import com.ciprianconstantin.queue.Queue;

/**
 * Created by churmuzache on 7/27/15.
 */
public class Producer<E extends Event> implements Runnable {

    private Queue<E> queue;
    private E event;

    public Producer(Queue<E> queue, E event) {
        this.queue = queue;
        this.event = event;
    }

    @Override
    public void run() {
        synchronized (queue) {
            System.out.println("Queue size: "+queue.length());
            //should make a check and if the queue is full then wait
            while (queue.isFull()) {
                System.out.println("Queue is full! Wait for a consumer to notify");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Producer " + Thread.currentThread().getId() + " is producing an event...");
            queue.offer(event);
            queue.notifyAll();
        }
    }

    public E getElement() {
        return event;
    }

    public void setElement(E element) {
        this.event = element;
    }
}
