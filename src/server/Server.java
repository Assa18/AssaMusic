package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private static Server instance = null;

    private ServerSocket serverSocket;

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }
    private Server() {
        try {
            serverSocket = new ServerSocket(15000);
            System.out.println("Server running! Waiting for requests...");
        } catch (IOException e) {
            System.out.println("Server: error while starting server!");
        }
    }

    public void run() {
        while (true) {
            try {
                new RequestHandler(serverSocket.accept()).start();
            } catch (IOException e) {
                System.out.println("Server: error while accepting requests!");
            }
        }
    }

    public static void main(String[] args) {
        Server server = Server.getInstance();
        server.run();
    }
}
