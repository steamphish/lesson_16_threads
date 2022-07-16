public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Running...");
                }
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Running #2...");
                }
            }
        };
        thread.run();
        thread1.run();
        thread.setPriority(0);
        thread1.setPriority(135);
        thread1.interrupt();
        System.out.println("Hello world!");
    }

    public void print() {
        synchronized (this) {
            System.out.println("Printing from...");
        }
    }
}