package hw05;

import java.util.Arrays;

public class HW05 {
    static final int SIZE = 10000000 ;
    static final int HALF = SIZE / 2 ;

    public static void main(String[] args) {
        System.out.println("Begin ... ");
        System.out.println("The array length is "+SIZE);

        final float [] arr1=initArray(SIZE); // создаем и заполняем массив единицами
        timing(()->consecutiveExecution(arr1),"Consecutive execution");

        final float [] arr2=initArray(SIZE);
        timing(()->ParallelExecution(arr2),"Parallel execution");
    }

    private static void consecutiveExecution(float arr[]) {
        fillArray(arr, 0);
    }


    private static void ParallelExecution (float arr[])  {
        float [] arr1 = new float [HALF];
        float [] arr2 = new float [HALF];

        System.arraycopy(arr,0,arr1,0,HALF);
        System.arraycopy(arr,HALF,arr2,0,HALF);

        Thread thread1 = new Thread(()->fillArray(arr1, 0));
        Thread thread2 = new Thread(()->fillArray(arr2, HALF));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);

    }

    private static float[] initArray(int size) {
        System.out.println("init the array...");
        float[] arr = new float[size];
        Arrays.fill(arr,1);
        return arr;
    }

    static void fillArray(float [] arr, int shift) {
        System.out.println("["+Thread.currentThread().getName()+"]: fill the array...");
        for(int i=0;i<(arr.length);i++) {
            arr[i] = funcValue(arr[i],i+shift);
        }
    }

    private static float funcValue(float idx, int i) {
        return  ( float )(  idx * Math . sin ( 0.2f + i/5) * Math . cos ( 0.2f + i / 5 ) *
                Math . cos ( 0.4f + i/ 2 ));

    }

    static void timing(Runnable method, String name) {
        long ts, tf;
        System.out.printf("%s start...%n", name);
        ts = System.currentTimeMillis();
        method.run();
        tf = System.currentTimeMillis();
        System.out.printf("%s time elapsed: %d(мс)%n", name,
                tf-ts);
    }
}



