package com.ciprianconstantin.factories;

import com.ciprianconstantin.consumer.Consumer;
import com.ciprianconstantin.queue.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by churmuzache on 7/27/15.
 */
public class ConsumerFactory {

    /** The consumers pool **/
    private ExecutorService executor = Executors.newCachedThreadPool();

    private List<Consumer> consumers;

    /** The consumers size **/
    private static int NR_CONSUMERS;

    /** The queue **/
    private Queue queue;

    public ConsumerFactory(Queue queue) {
        NR_CONSUMERS=10000;
        this.queue=queue;
        consumers = new ArrayList<Consumer>(NR_CONSUMERS);
        for(int i=0;i<NR_CONSUMERS;i++) {
            consumers.add(new Consumer(queue));
        }
    }

    public ConsumerFactory(Queue queue, int size) {
        NR_CONSUMERS=size;
        this.queue=queue;
        consumers = new ArrayList<Consumer>(NR_CONSUMERS);
        for(int i=0;i<NR_CONSUMERS;i++) {
            consumers.add(new Consumer(queue));
        }
    }

    /**
     * init method that will start the
     */
    public void init() {
        for(int i=0;i<NR_CONSUMERS;i++) {
            executor.execute(consumers.get(i));
        }
    }

    public void shutdown() {
        this.executor.shutdown();
    }

}
