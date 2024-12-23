package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler extends Thread {
    private Socket socket;

    private enum command {
        PLAYCOMMAND,
        SEARCHCOMMAND
    }
    private RequestCommand[] commandPool;

    public RequestHandler(Socket socket) {
        this.socket = socket;
        commandPool = new RequestCommand[command.values().length];
        commandPool[0] = new PlayCommand();
        commandPool[1] = new SearchCommand();
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int type = Integer.parseInt(br.readLine());
            System.out.println(type +"");
            String msg = br.readLine();
            System.out.println("Message got! " + msg);

            commandPool[type].execute(socket, msg);
        } catch (IOException e) {
            System.out.println("Error handling request!");
        }
    }
}
