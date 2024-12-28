package server.command;

import server.MusicProvider;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class RandomCommand implements RequestCommand {
    private MusicProvider provider;

    public RandomCommand() {
        provider = MusicProvider.getInstance();
        provider.load();
    }
    @Override
    public void execute(Socket socket, String par) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            int number = Integer.parseInt(par);
            for (int i = 0; i < number; i++) {
                bw.write(provider.getRandom().toString() + "\n");
                bw.flush();
            }

            System.out.println("Server: sent " + number + " songs");
            bw.write("stop\n");
            bw.flush();
        } catch (IOException e) {
            System.out.println("Server: error with sending random musics");
        }
    }
}
