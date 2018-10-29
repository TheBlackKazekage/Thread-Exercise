package com.company;

import java.util.Arrays;

public class MaxValue extends Thread{

    private int low, high;
    private int[] array;
    private int max;

    private MaxValue(int[] arr, int low, int high){
        this.array = arr;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run() {
        max = low;
        for(int i = low; i < high; i++){
            if(array[i] > max) max = array[i];
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[20];

        for(int i = 0; i < arr.length; i++){
            arr[i] = (int) (Math.random() * 100);
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(maxValue(arr));
    }

    private static int maxValue(int[] arr) throws InterruptedException {
        //get length of the array
        //create array of threads
        //assign index lengths to those threads
        int len = arr.length;
        int maxValue = 0;

        MaxValue[] max = new MaxValue[4];
        for(int i = 0; i < 4; i++){
            max[i] = new MaxValue(arr, (i * len)/4, ((i + 1) * len) / 4);
            max[i].start();
        }

        for(int i = 0; i < 4; i++){
            max[i].join();
            if(max[i].max > maxValue) maxValue = max[i].max;
        }

        return maxValue;
    }


}
