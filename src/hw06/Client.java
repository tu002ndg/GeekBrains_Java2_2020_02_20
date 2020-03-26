package hw06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String END_COMMAND = "/end";
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8180;


    void run()  throws IOException {
        Socket socket = null;
        Thread inputStreamThread = null;

        try {

            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            inputStreamThread = inputStream(in);
            outputStream(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStreamThread!= null) inputStreamThread.interrupt();
            if (socket != null) socket.close();
        }

    }


    public Thread inputStream(DataInputStream in) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String message = in.readUTF();
                    System.out.println("From server: " + message);
                } catch (IOException e) {
                    System.out.println("The connection was lost or closed");
                    break;
                }
            }
        });
        thread.start();
        return thread;
    }

    private void outputStream(DataOutputStream out) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.nextLine();
            if (message.equals(END_COMMAND)) {
                break;
            }
            System.out.println("To server: " + message);
            out.writeUTF(message);
        }
        sc.close();
    }

    public static void main(String[] args) throws IOException {
        new Client().run();
    }


}
