package hw02;

public class HW2 {
   // work with exception
    /*
    1. Напишите метод, на вход которого подаётся двумерный строковый массив
    размером 4х4,
    при подаче массива другого размера необходимо бросить
    исключение MyArraySizeException.
    2. Далее метод должен пройтись по всем элементам массива,
    преобразовать в int, и просуммировать. Если в каком-то
    элементе массива преобразование не удалось (например,
    в ячейке лежит символ или текст вместо числа),
    должно быть брошено исключение MyArrayDataException,
    с детализацией в какой именно ячейке лежат неверные данные.
    3. В методе main() вызвать полученный метод, обработать
    возможные исключения MySizeArrayException и MyArrayDataException,
    и вывести результат расчета.
    * */
    public static void main(String[] args) {
        String[][][] arr = {
                {{"5", "4", "0"},
                 {"0", "9", "4"},
                 {"1", "3" }
                },
                {{"1","2","3","4"},
                 {"5","6","tfj","8"},
                 {"1","2","3","4"},
                 {"5","6","7","8"}
                },
                {{"1","2","3","4"},
                {"5","6","7","8"},
                {"1","2","3","4"},
                {"5","6","7","8"}
                }};

        for (String[][] a: arr)
        try {
            workUpArray(a); }
            catch (MyArraySizeException | MyArrayDataException e) {
                System.out.println(e.getMessage());
            }
    }

    private static void workUpArray(String[][] arr)
            throws MyArraySizeException, MyArrayDataException{
        System.out.println("The array received: ");
        printArray(arr);

        //check array
        System.out.println("Check the array size ... ");
        if (checkArray(arr)) {
            System.out.println("The array size is correct");
        } else {
            throw new MyArraySizeException();
        }

        // try to sum elements
        System.out.println("Try to sum the array's elements...");
        System.out.printf("The total sum is %d%n", getSum(arr));

    }

    private static void printArray(String[][] arr) {
        for(String[] sl: arr) {
            for (String s: sl) {
                System.out.print(s+"\t");
            }
            System.out.println();
        }
    }

    private static long getSum(String[][] arr) throws MyArrayDataException {
        long sum=0;

        for (int i=0; i<4; i++)
            for (int j=0; j<4; j++) {
             try {
                 sum+=Integer.parseInt(arr[i][j]);
             } catch (NumberFormatException e) {
                throw  new MyArrayDataException(i,j);
             }
         }
        return sum;
    }

    private static boolean checkArray(String[][] arr) {
        return ((arr!= null) && (arr.length==4)
                && (arr[0].length==4)
                && (arr[1].length==4)
                && (arr[2].length==4)
                && (arr[3].length==4));
    }

}
