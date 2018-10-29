package com.company;

public class SharedCounter extends Thread {
    private static int counter = 0;
    private static int start = 0;
    private static int end = 0;

    @Override
    public void run() {
        start = (int) System.nanoTime();
        System.out.println("start time: " + this.getName() + " " + start);

        for(int i = 0; i < 10; i++){
            counter++;
            System.out.println("before: " + this.getName() + "  " + counter);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after: " + this.getName() + "  " + counter);
        }

        end = (int) System.nanoTime();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Initial Value: " + counter);

        SharedCounter[] threads = new SharedCounter[10];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new SharedCounter();
            threads[i].start();
            //System.out.println("init: " + counter);
        }

        for (SharedCounter thread : threads) {
            //System.out.println("initial " + counter);
            thread.join();
            //System.out.println("after join: " + counter);
            System.out.println("Time of execution: " + thread.getName() + " " + (end - start));
        }

        System.out.println("Final Value: " + counter);
    }
}
