package server.command;

import server.MusicProvider;

import java.io.*;
import java.net.Socket;

public class PlayCommand implements RequestCommand{
    private MusicProvider provider;

    public PlayCommand() {
        provider = MusicProvider.getInstance();
        provider.load();
    }
    @Override
    public void execute(Socket socket, String par) {
        try {
            FileInputStream inputStream = new FileInputStream(par);
            OutputStream outputStream = socket.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;

            System.out.println("Server: the music is on its way...");
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer,0,bytesRead);
            }

            inputStream.close();
            socket.close();

            System.out.println("Server: music sent!");
        } catch (IOException e) {
            System.out.println("Server error with sending music source!");
        }
    }
}
