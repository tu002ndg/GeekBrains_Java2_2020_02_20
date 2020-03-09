package hw02;

public class MyArraySizeException extends IllegalArgumentException{

    public MyArraySizeException() {
        super("Ошибочный размер массива");
    }

}
