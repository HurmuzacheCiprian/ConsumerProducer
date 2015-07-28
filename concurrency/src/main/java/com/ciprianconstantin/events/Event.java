package com.ciprianconstantin.events;

/**
 * Created by churmuzache on 7/27/15.
 */
public class Event<E> {

    private E element;

    public Event(E element) {
        this.element=element;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "Event{" +
                "element=" + element +
                '}';
    }
}
