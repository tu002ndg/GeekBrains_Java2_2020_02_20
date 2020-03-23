package hw06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {


    public static final String END_COMMAND = "/end";
    private static final int SERVER_PORT = 8180;


    void run()  throws IOException {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        Thread inputStreamThread = null;

       try {
           serverSocket = new ServerSocket(SERVER_PORT);
           System.out.println("Сервер запущен, ожидаем подключения...");
           clientSocket = serverSocket.accept();
           System.out.println("Клиент подключился");
           DataInputStream in = new DataInputStream(clientSocket.getInputStream());
           DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
           inputStreamThread = inputStream(in);
           outputStream(out);
       } catch (IOException e) {
            e.printStackTrace();
       } finally {
           if(inputStreamThread!= null) inputStreamThread.interrupt();
           if (serverSocket != null) serverSocket.close();
           if (clientSocket != null) clientSocket.close();
       }

    }

    private void outputStream(DataOutputStream out) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
                String message = sc.nextLine();
            if (message.equals(END_COMMAND)) {
                break;
            }
            System.out.println("To client: " + message);
            out.writeUTF(message);
        }
        sc.close();
    }

    public Thread inputStream(DataInputStream in) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    String message = in.readUTF();
                    System.out.println("From client: " + message);
                } catch (IOException e) {
                    System.out.println("The connection was lost or closed");
                    break;
                }
            }
        });
        thread.start();
        return thread;
    }


    public static void main(String[] args) throws IOException {
        new Server().run();
    }

}
