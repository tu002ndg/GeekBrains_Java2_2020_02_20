package hw05;

public class HW05 {
    static final int SIZE = 10000000 ;
    static final int HALF = SIZE / 2 ;

    static class MyThread extends Thread {
        private float [] arr;
        private int start;

        public MyThread(float [] arr, int start) {
            this.arr = arr;
            this.start = start;
        }

        @Override
        public void run() {
            System.out.println("["+
                    Thread.currentThread().getName()+
                    "] : start = "+start);
            HW05.fill_array(arr, start);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        float [] arr = new float [SIZE];
        long ts;
        System.out.println("Begin ... ");
        System.out.println("The array length is "+SIZE);

        init_array(arr); // заполняем массив единицами

        System.out.println("method1 start...");
        ts = System.currentTimeMillis();
        method1(arr);
        System.out.printf("method1 time elapsed: %d(мс)%n", System.currentTimeMillis()-ts);

        init_array(arr);

        System.out.println("method2 start...");
        ts = System.currentTimeMillis();
        method2(arr);
        System.out.printf("method2 time elapsed: %d(мс)%n", System.currentTimeMillis()-ts);
    }

    private static void method1(float arr[]) {
        fill_array(arr, 0);
    }


    private static void method2 (float arr[]) throws InterruptedException {
        float [] arr1 = new float [HALF];
        float [] arr2 = new float [HALF];

        System.arraycopy(arr,0,arr1,0,HALF);
        System.arraycopy(arr,HALF,arr2,0,HALF);

        Thread thread1 = new MyThread(arr1, 0);
        Thread thread2 = new MyThread(arr2, HALF);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);

    }

    private static void init_array(float [] arr) {
        System.out.println("init the array...");
        for(int i=0;i<arr.length;i++)
            arr[i]=1;
    }

    static void fill_array(float [] arr, int start) {
        System.out.println("["+Thread.currentThread().getName()+"]: fill the array...");
        for(int i=start;i<(arr.length+start);i++) {
            arr[i-start] = ( float )( arr [ i -start] * Math . sin ( 0.2f + i / 5 ) * Math . cos ( 0.2f + i / 5 ) *
                    Math . cos ( 0.4f + i / 2 ));
        }
    }

}

