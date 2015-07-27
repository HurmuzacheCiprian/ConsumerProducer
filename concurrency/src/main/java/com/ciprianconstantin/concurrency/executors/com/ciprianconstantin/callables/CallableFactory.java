package com.ciprianconstantin.concurrency.executors.com.ciprianconstantin.callables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Created by churmuzache on 7/27/15.
 */
public abstract class CallableFactory {

    public static <T> Callable<T> createCallable(final T entity) {
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                return entity;
            }
        };
    }

    /**
     * A collection of callables containing the E entity
     * @param nrElements
     * @param entity
     * @param <E>
     * @return
     */
    public static <E> Collection<Callable<E>> createCallableCollection(final int nrElements, final E entity) {
        Collection<Callable<E>> collectables = new ArrayList<Callable<E>>();

        for(int i=0;i<nrElements;i++) {
            collectables.add(new Callable<E>() {
                @Override
                public E call() throws Exception {
                    return entity;
                }
            });
        }

        return collectables;
    }


}
