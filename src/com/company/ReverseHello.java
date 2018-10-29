package com.company;

public class ReverseHello extends Thread {
    private int counter;

    private ReverseHello(int counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        if(counter < 50){
            try {
                createThread(counter+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Hello from Thread " + counter + "!");
    }

    public static void createThread(int counter) throws InterruptedException{
        ReverseHello t = new ReverseHello(counter);
        t.start();
        t.join();
    }

    public static void main(String[] args){
        ReverseHello hello = new ReverseHello(1);
        hello.start();
    }
}
