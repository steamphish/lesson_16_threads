package ex_04;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        YearRun yearRun = new YearRun();
        Thread yearThread = new Thread(yearRun);
        MonthRun monthRun = new MonthRun();
        Thread monthThread = new Thread(monthRun);
        DayRun dayRun = new DayRun();
        Thread dayThread = new Thread(dayRun);
        yearThread.start();
        //yearThread.join();
        monthThread.start();
        //monthThread.join();
        dayThread.start();
    }
}
class DayRun implements Runnable {
    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Day: "+calendar.get(Calendar.DAY_OF_MONTH));
    }
}
class MonthRun implements Runnable {

    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Month: "+calendar.get(Calendar.MONTH));
    }
}

class YearRun implements Runnable {

    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Year: "+calendar.get(Calendar.YEAR));
    }
}
