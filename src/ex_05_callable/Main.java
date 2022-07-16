package ex_05_callable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Set<Callable<Integer>> callables = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            int a = ThreadLocalRandom.current().nextInt(100);
            int b = ThreadLocalRandom.current().nextInt(100);
            callables.add(getSum(a, b));
        }
        try {
            List<Future<Integer>> futures = executorService.invokeAll(callables);
            for (Future<Integer> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private static Callable<Integer> getSum(int a, int b) {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.printf(String.format("%s is performing...[%%d+%%d]\n", Thread.currentThread().
                        getName()), a, b);
                Thread.sleep(100);
                return a + b;
            }
        };
    }
}
