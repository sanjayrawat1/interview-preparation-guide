package com.github.sanjayrawat1.interview.concurrent.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Common Thread Pool Types (from Executors):
 * <li>
 *     newFixedThreadPool(int nThreads): You tell it how many threads you want, and it sticks to that number.
 *     Good for predictable loads.
 * </li>
 * <li>
 *     newCachedThreadPool(): Creates new threads as needed, but reuse old ones if they're free.
 *     Great for tasks that come and go quickly.
 * </li>
 * <li>
 *     newSingleThreadExecutor(): Only one thread, ever. Ensures tasks are executed one after another, in order.
 * </li>
 * <li>
 *     newScheduledThreadPool(int corePoolSize): If you need to run tasks after a delay or on a schedule
 *     (like every minute), this is your guy.
 * </li>
 * @author sanjayrawat1
 */
public class ExecutorServiceExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // let's get a thread pool with 2 threads.
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // here's a simple task to run
        executor.execute(() -> {
            System.out.println("Hey, this Runnable task is on " + Thread.currentThread().getName());
        });
        // Now, a task that actually *returns* something! (Callable)
        Callable<String> callableTask = () -> {
            System.out.println("This callable task is also on " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            return "Task Finished!";
        };
        Future<String> future = executor.submit(callableTask);
        // while that's running, your main program can do other stuff...
        System.out.println("Main thread is busy doing its own thing...");
        // when you need the result, just call .get() on the future. It'll wait if it's not ready.
        String result = future.get();
        System.out.println("Got this from the callable : " + result);
        // don't forget to shut down your executor when you're done!
        executor.shutdown(); // tells it to stop accepting new tasks
        executor.awaitTermination(5, TimeUnit.SECONDS); // waits up to 5 seconds for tasks to finish
        System.out.println("Executor service said goodbye.");
    }
}
