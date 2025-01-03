package server;

import server.command.PlayCommand;
import server.command.RandomCommand;
import server.command.RequestCommand;
import server.command.SearchCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler extends Thread {
    private Socket socket;

    private RequestCommand[] commandPool;

    public RequestHandler(Socket socket) {
        this.socket = socket;
        commandPool = new RequestCommand[3];
        commandPool[0] = new PlayCommand();
        commandPool[1] = new SearchCommand();
        commandPool[2] = new RandomCommand();
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int type = Integer.parseInt(br.readLine());
            System.out.println("Server: " + type);
            String msg = br.readLine();
            System.out.println("Server: message got! " + msg);

            commandPool[type].execute(socket, msg);
        } catch (IOException e) {
            System.out.println("Server: error handling request!");
        }
    }
}
