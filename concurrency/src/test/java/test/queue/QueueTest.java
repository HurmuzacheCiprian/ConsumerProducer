package test.queue;

import com.ciprianconstantin.queue.Queue;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by churmuzache on 7/27/15.
 */
public class QueueTest {

    @Test
    public void testEmptyQueue() {
        Queue<String> stringQueue = new Queue<String>();
        Assert.assertEquals(0, stringQueue.length());
    }

    @Test
    public void testElementsQueue() {
        Queue<String> stringQueue = new Queue<String>();

        stringQueue.offer("a");
        stringQueue.offer("b");


        Assert.assertEquals(2,stringQueue.length());
    }

    @Test
    public void testMoreElementsQueue() {
        Queue<String> stringQueue = new Queue<String>();

        for(int i=0;i<1000;i++) {
            stringQueue.offer(""+i);
        }

        Assert.assertEquals(1000,stringQueue.length());
        Assert.assertEquals("0",stringQueue.peek());
    }

    @Test
    public void testRemovalOfElements() {
        Queue<String> stringQueue = new Queue<String>();

        stringQueue.offer("a");
        stringQueue.offer("b");
        stringQueue.offer("c");
        stringQueue.offer("d");
        stringQueue.offer("e");

        Assert.assertEquals(5, stringQueue.length());
        String removedString = stringQueue.poll();
        Assert.assertEquals(4, stringQueue.length());

        for(int i=0;i<4;i++) {
            stringQueue.poll();
        }
        Assert.assertEquals(0,stringQueue.length());
        Assert.assertTrue(stringQueue.isEmpty());

        try {
            stringQueue.poll();
        } catch(Exception exception) {
            Assert.assertEquals("The queue is empty",exception.getMessage());
        }
    }

    @Test
    public void testFullQueue() {
        Queue<String> stringQueue = new Queue<>();
        for(int i=0;i<10000;i++) {
            stringQueue.offer(""+i);
        }

        try {
            stringQueue.offer("test");
            Assert.fail("should not fail here");
        } catch(Exception exception) {
            Assert.assertEquals("The queue is full",exception.getMessage());
        }

    }

}
