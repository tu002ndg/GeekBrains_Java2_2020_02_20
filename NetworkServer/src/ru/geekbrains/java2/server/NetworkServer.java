package ru.geekbrains.java2.server;

import ru.geekbrains.java2.server.auth.AuthService;
import ru.geekbrains.java2.server.auth.BaseAuthService;
import ru.geekbrains.java2.server.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetworkServer {
    private final int port;
    private final List<ClientHandler> clients =
            new ArrayList<>();
    private final AuthService authService;

    public NetworkServer(int port) {
        this.port = port;
        this.authService = new BaseAuthService();
    }

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.printf(
                    "Сервер был успешно запущен на порту %s%n",
                    port);
            authService.start();
            while (true) {
                System.out.println("Ожидание подключения клиента...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился");
                createClientHandler(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при работе сервера");
            e.printStackTrace();
        } finally {
            authService.stop();
        }
    }

    private void createClientHandler(Socket clientSocket) {
        ClientHandler clientHandler =
                new ClientHandler(this,clientSocket);
        clientHandler.run(clientSocket);

    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void broadcastMessage(String message,  ClientHandler owner)
            throws IOException {
        for (ClientHandler client: clients
             ) {
            if (client!=owner)
                client.sendMessage(message);
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}
