package com.example.assignment2.Network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService backgroundExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
}
