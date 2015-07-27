package com.ciprianconstantin.queue;

/**
 * Created by churmuzache on 7/27/15.
 */
public class Queue<E> {

    /**
     * The node class
     * @param <E>
     */
    private class QueueNode<E> {
        private E element;
        private QueueNode<E> next;
        private QueueNode<E> prev;

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

    // TODO
    public void offer(E element) {

    }

    //TODO
    public E poll() {
        return null;
    }

}
