package com.ciprianconstantin.concurrency.executors;

import java.util.concurrent.ExecutionException;

/**
 * Created by churmuzache on 7/27/15.
 */
public class MainClass {

    public static void main(String[] args) {
        ExecutorWrapper executorWrapper = new ExecutorWrapper();
        Object response = null;
        try {
            response = executorWrapper.doTask(10);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response);

        executorWrapper.shutdown();
    }

}
