package com.ciprianconstantin.queue;

/**
 * Created by churmuzache on 7/27/15.
 */
public class Queue<E> {

    /**
     * The node class
     *
     * @param <E>
     */
    private class QueueNode<E> {
        private E element;
        private QueueNode<E> next;
        private QueueNode<E> prev;

        private QueueNode(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public QueueNode<E> getNext() {
            return next;
        }

        public void setNext(QueueNode<E> next) {
            this.next = next;
        }

        public QueueNode<E> getPrev() {
            return prev;
        }

        public void setPrev(QueueNode<E> prev) {
            this.prev = prev;
        }

    }

    /**
     * First sentinel *
     */
    private QueueNode<E> first;

    /**
     * Last sentinel *
     */
    private QueueNode<E> last;

    /**
     * The length of the queue *
     */
    private int length = 0;
    /*
        The size of the queue
     */
    private static int QUEUE_SIZE;

    private boolean notifyConsumers;

    private boolean notifyProducers;

    /**
     *  Default queue size of 10000
     */
    public Queue() {
        first = createNode(null);
        last = createNode(null);
        this.notifyConsumers=false;
        this.notifyProducers=false;

        first.setNext(last);
        last.setPrev(first);

        QUEUE_SIZE = 10000;
    }

    /**
     * Constructor size
     */
    public Queue(int size) {
        first = createNode(null);
        last = createNode(null);

        this.notifyConsumers=false;
        this.notifyProducers=false;

        first.setNext(last);
        last.setPrev(first);

        QUEUE_SIZE=size;
    }

    /**
     * Simple queue insertion
     *
     * @param element
     */
    public void offer(E element) {
        QueueNode<E> newNode = createNode(element);

        if(this.length < QUEUE_SIZE) {
            newNode.setNext(this.first.getNext());
            this.first.getNext().setPrev(newNode);
            this.first.setNext(newNode);
            newNode.setPrev(this.first);
            this.length++;
        }else {
            throw new RuntimeException("The queue is full");
        }
    }

    /**
     * Simple removal of the last element
     *
     * @return
     */
    public E poll() {
        E deletedElement = null;
        if (this.length == 0) {
            throw new RuntimeException("The queue is empty");
        } else {
            QueueNode<E> toBeRemoved = this.last.getPrev();

            this.last.setPrev(toBeRemoved.getPrev());
            this.last.getPrev().setNext(this.last);

            deletedElement = toBeRemoved.getElement();
            toBeRemoved = null;   //remove obsolete reference

            this.length--;
        }
        return deletedElement;
    }

    public E peek() {
        if (this.length > 0) {
            return this.last.getPrev().getElement();
        }
        return null;
    }

    public boolean isEmpty() {
        return this.first.next == this.last;
    }

    public boolean isFull() {
        return this.length == QUEUE_SIZE;
    }

    public int length() {
        return this.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        QueueNode<E> temp = this.first.getNext();
        while (temp != this.last) {
            sb.append(temp.getElement());
            sb.append(",");
            temp = temp.getNext();
        }


        if(sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Simple factory method for a node
     *
     * @param element
     * @return
     */
    private QueueNode<E> createNode(E element) {
        QueueNode<E> node = new QueueNode<E>(element);
        node.setNext(null);
        node.setPrev(null);
        return node;
    }
}
