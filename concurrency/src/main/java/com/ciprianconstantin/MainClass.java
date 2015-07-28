package com.ciprianconstantin;

import com.ciprianconstantin.events.Event;
import com.ciprianconstantin.factories.ConsumerFactory;
import com.ciprianconstantin.factories.ProducerFactory;
import com.ciprianconstantin.queue.Queue;

/**
 * Created by churmuzache on 7/27/15.
 */
public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        Queue<Event<String>> queue = new Queue<Event<String>>();

        ConsumerFactory consumerFactory = new ConsumerFactory(queue, 3);
        consumerFactory.init();

        ProducerFactory producerFactory = new ProducerFactory(queue, 2);


        for (int i = 0; i < 20; i++) {
            Event<String> stringEvent = new Event<>("string " + i);
            producerFactory.produce(stringEvent);
        }

    }

}
