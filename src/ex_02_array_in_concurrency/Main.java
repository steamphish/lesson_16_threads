package ex_02_array_in_concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        /*Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of row: ");
        int row = sc.nextInt();
        System.out.println("Enter number of column: ");
        int column = sc.nextInt();
        int[][] massive = new int[row][column];
        List<Thread> threadList = new ArrayList<>();
        long time = System.currentTimeMillis();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j<column; j++) {
                massive[i][j] = ThreadLocalRandom.current() .nextInt(0, 100);
            }
        }
        System.out.println(System.currentTimeMillis() - time);
        for (int[] m : massive) {
            System.out.println(Arrays.toString(m));
        }*/

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of row: ");
        int row = sc.nextInt();
        System.out.println("Enter number of column: ");
        int column = sc.nextInt();
        int[][] massive = new int[row][column];
        List<Thread> threadList = new ArrayList<>();
        long time = System.currentTimeMillis();
        for (int i = 0; i < row; i++) {
            RowThread rowThread = new RowThread("RowThread", massive[i]);
            threadList.add(rowThread);
            rowThread.start();
        }
        System.out.println(System.currentTimeMillis() - time);
        boolean flag = true;
        while (flag) {
            for (Thread t : threadList) {
                if (!t.isAlive()) {
                    flag = false;
                }
                System.out.println(t.getName() + " " + t.getId() + " - " + t.isAlive());
            }
        }
        System.out.println("The last thread was died!");
        for (int[] m : massive) {
            System.out.println(Arrays.toString(m));
        }
    }
}

class RowThread extends Thread {
    private int[] row;

    RowThread(String name, int[] row) {
        super(name);
        this.row = row;
    }

    @Override
    public void run() {
        System.out.printf("%s-%s started... \n", Thread.currentThread().getName(),
                Thread.currentThread().getId());
        for (int i = 0; i<row.length; i++) {
            row[i] = getRandomNumber();
        }
        System.out.printf("%s-%s finished... \n", Thread.currentThread().getName(), Thread.currentThread().getId());
    }

    private int getRandomNumber() {
        return ThreadLocalRandom.current() .nextInt(0, 100);
    }
}