package com.ciprianconstantin.factories;

import com.ciprianconstantin.events.Event;
import com.ciprianconstantin.producer.Producer;
import com.ciprianconstantin.queue.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by churmuzache on 7/27/15.
 */
public class ProducerFactory {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private List<Producer> producers;

    /** The queue **/
    private Queue queue;

    /** The producers size **/
    private static int PROD_NR;

    /** the current producer **/
    private int currentProducerIndex;

    public ProducerFactory(Queue queue, int size) {
        this.queue=queue;
        this.PROD_NR=size;
        this.currentProducerIndex = 0;
        producers = new ArrayList<>(PROD_NR);
        for(int i=0;i<PROD_NR;i++) {
            producers.add(new Producer(queue,null));
        }
    }

    public ProducerFactory(Queue queue) {
        this.queue=queue;
        PROD_NR=1000;
        this.currentProducerIndex = 0;
        producers = new ArrayList<>(PROD_NR);
        for(int i=0;i<PROD_NR;i++) {
            producers.add(new Producer(queue,null));
        }
    }

    /**
     *
     * @param event
     * @param <E>
     */
    public <E> void produce(Event<E> event ) {
        //maybe the producers should be executed at the beginning.

        if(currentProducerIndex == PROD_NR-1) {
            currentProducerIndex = 0;
        }
        Producer currentProducer = producers.get(currentProducerIndex);
        currentProducer.setElement(event);
        currentProducerIndex++;
        executor.execute(currentProducer);

    }
}
