package com.ciprianconstantin;

import com.ciprianconstantin.events.Event;
import com.ciprianconstantin.factories.ConsumerFactory;
import com.ciprianconstantin.factories.ProducerFactory;
import com.ciprianconstantin.queue.Queue;

/**
 * Created by churmuzache on 7/27/15.
 */
public class MainClass {

    public static void main(String[] args) {
        Queue<Event<String>> queue = new Queue<Event<String>>();

        ConsumerFactory consumerFactory = new ConsumerFactory(queue,10);
        consumerFactory.init();

        ProducerFactory producerFactory = new ProducerFactory(queue, 5);


        for(int i=0;i<100;i++) {
            Event<String> stringEvent = new Event<>("string "+i);
            producerFactory.produce(stringEvent);
        }   

    }

}
