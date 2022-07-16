package ex_07_volatile;

public class TaskRunner {

    private static int number;
    private static boolean ready;

    private static class Reader extends Thread {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        while (true) {
            new Reader().start();
            number = 42;
            ready = true;
        }
    }
}