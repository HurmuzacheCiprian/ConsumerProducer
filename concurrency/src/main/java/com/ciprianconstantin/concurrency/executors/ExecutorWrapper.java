package com.ciprianconstantin.concurrency.executors;

import java.util.*;
import java.util.concurrent.*;

/**
 *
 *
 * Created by churmuzache on 7/27/15.
 */
public class ExecutorWrapper {

    private static final int N_THREADS = 10;

    private final ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);

    public Object doTask(final int i) throws ExecutionException, InterruptedException {
        Future<String> integerFuture = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Inside callable method call");
                return "Done";
            }
        });
        return integerFuture.get();
    }

    /**
     *
     * @param callableSet
     * @param <T>
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public <T> List<T> doTask(Collection<Callable<T>> callableSet) throws ExecutionException, InterruptedException {
        final Map<Future<T>, Object> futureMap = new HashMap<Future<T>, Object>(callableSet.size());
        List<T> responseList = new ArrayList<T>(callableSet.size());
        if(callableSet == null || callableSet.isEmpty()) {
            throw new RuntimeException("No callables provided");
        }
        for(Callable<T> c : callableSet) {
            Future<T> async = executor.submit(c);
            futureMap.put(async,null);
        }

        for(Future<T> f : futureMap.keySet()) {
            responseList.add(f.get());
        }

        return responseList;
    }

    public void shutdown() {
        executor.shutdown();
    }

}
