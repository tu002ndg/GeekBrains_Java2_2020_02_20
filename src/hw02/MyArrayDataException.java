package hw02;

public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(int i, int j) {
        super("Неверные данные.\nЭлемент массива arr["+i+"]["+j+"] не является числом!");
    }
}
