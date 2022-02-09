package myThread;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ThreadHomework {
    private static final int SIZE = 100;
    private static final int HALF = SIZE/2;
    static float[] arr = new float[SIZE];
    static float [] arr2 = new float[SIZE];

    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        secondMethod();
        //splitAndMergeExample();
    }
           public static void firstMethod(){

               for (int i = 0; i<SIZE; i++){

                   arr[i] = 1.0f;

               }
               //System.out.println(Arrays.toString(arr));

               long startTime = System.currentTimeMillis();
               for (int i =0; i<SIZE; i++){
                   arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
               }
               System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
               System.out.println(  Arrays.toString(arr));
               System.out.println( );
         }


           public static void secondMethod() throws InterruptedException {
        for (int i=0; i<SIZE; i++){
            arr[i] =  1.0f;
        }
               //System.out.println(Arrays.toString(arr));
// Создаем переменную и записываем в нее текущее время
               long startTime = System.currentTimeMillis();

        float[] leftHalf = new float[HALF];
        float[] rightHalf = new float[HALF];
// копируем значения из массива arr с 0 в массив left с 0 индекса до половины
        System.arraycopy(arr,0,leftHalf,0,HALF);
// копируем значения из заданного массива с середины в массив right (заполняем его с 0 до последнего индекса)
        System.arraycopy(arr,HALF,rightHalf,0,HALF);
              // System.out.println(Arrays.toString(leftHalf));
               //System.out.println(Arrays.toString(rightHalf));
// создаем первый поток
               Thread leftThread = new Thread(new Runnable() {
                   @Override
                   public void run() {
// просчитываем первый массив
                       for (int i = 0; i < HALF; i++) {
                           leftHalf[i] = (float) (leftHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                       }
                   }
               });


              // System.out.println(Arrays.toString(leftHalf));
// Создаем второй поток

               Thread rightThread = new Thread(new Runnable() {
                   @Override
                   public void run() {
// просчитываем второй массив
                       for (int i = 0; i < HALF; i++) {
                           rightHalf[i] = (float) (rightHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                       }

                   }

               });
               //System.out.println(Arrays.toString(rightHalf));
// Запускаем потоки
               rightThread.start();
               leftThread.start();
// ждем завершения работы потоков
               rightThread.join();
               leftThread.join();

// склеиваем оба массива обратно
// из левого массива берем с 0 элемента и записываем в новый массив с 0 до середины

               System.arraycopy(leftHalf,0,arr2,0,HALF);

// из правого массива берем с 0 элемента и записываем в новый массив с середины до второй половины

               System.arraycopy(rightHalf,0,arr2,HALF,HALF);
 // Выводим результат на экран
               System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
               System.out.println(Arrays.toString(arr2));





      //  Thread thread1 = new Thread();
       // Thread thread2 = new Thread();
           }


    public static void splitAndMergeExample() {

        for (int i=0; i<SIZE; i++) {
            arr[i] = i;
        }
       // int[] initialArray = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(arr));

        float[] leftHalf = new float[HALF];
        float [] rightHalf = new float[HALF];
        System.arraycopy(arr, 0, leftHalf, 0, 3);
        System.arraycopy(arr, 3, rightHalf, 0, 3);
        System.out.println(Arrays.toString(leftHalf));
        System.out.println(Arrays.toString(rightHalf));

        float[] mergedArray = new float[SIZE];
        System.arraycopy(leftHalf, 0, mergedArray, 0, HALF);
        System.arraycopy(rightHalf, 0, mergedArray, HALF, HALF);
        System.out.println(Arrays.toString(mergedArray));
    }



}




