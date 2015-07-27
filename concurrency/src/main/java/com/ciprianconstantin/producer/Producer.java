package com.ciprianconstantin.producer;

import com.ciprianconstantin.queue.Queue;

/**
 * Created by churmuzache on 7/27/15.
 */
public class Producer<E> implements Runnable {

    private Queue<E> queue;

    @Override
    public void run() {
        System.out.println("About to consume the message");

    }

}
