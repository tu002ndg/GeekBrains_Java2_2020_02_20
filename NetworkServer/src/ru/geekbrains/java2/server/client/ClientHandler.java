package ru.geekbrains.java2.server.client;

import ru.geekbrains.java2.server.NetworkServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private final NetworkServer networkServer;
    private final Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;



    public ClientHandler(NetworkServer networkServer, Socket socket) {
        this.networkServer = networkServer;
        this.clientSocket = socket;

    }

    public void run(Socket socket) {
        doHandle(socket);
    }


    private void doHandle(Socket socket) {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(()->{
                try {
                    authentication();
                    readMessage();
                } catch (IOException e) {
                    System.out.printf("Соединение с клиентом %s было разорвано%n",
                            nickname);
                }
                finally {
                    closeConnection();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        networkServer.unsubscribe(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            System.out.printf("От %s: %s%n",nickname, message);
            if ("/end".equals(message)) {
                return;
            }
            networkServer.broadcastMessage(nickname+": "+message, this);

        }
    }

    private void authentication() throws IOException {
        while (true) {
            String message = in.readUTF();
            // "/auth login password
           if (message.startsWith("/auth")) {
               String[] messageParts = message.split("\\s+", 3);
               String login = messageParts[1];
               String password = messageParts[2];
               String username = networkServer.getAuthService()
                       .getUsernameByLoginAndPassword(login, password);
               if (username == null) {
                   sendMessage("Отсутствует учетная запись с данным логином и паролем");
               } else {
                   nickname = username;
                   networkServer.broadcastMessage(nickname + " зашел в чат!",this);
                   sendMessage("/auth " + nickname);
                   networkServer.subscribe(this);
                   break;
               }
           }
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }
}
