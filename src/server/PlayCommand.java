package server;

import java.io.*;
import java.net.Socket;

public class PlayCommand implements RequestCommand{
    MusicProvider provider;

    public PlayCommand() {
        provider = MusicProvider.getInstance();
        provider.load();

        provider.save();
    }
    @Override
    public void execute(Socket socket, String par) {
        try {
            FileInputStream inputStream = new FileInputStream(par);
            OutputStream outputStream = socket.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;

            System.out.println("the music is on its way...");
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer,0,bytesRead);
            }

            inputStream.close();
            socket.close();

            System.out.println("music sent!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
