package server;

import java.io.*;
import java.net.Socket;

public class SearchCommand implements RequestCommand{

    private MusicProvider provider = MusicProvider.getInstance();

    public SearchCommand() {
        provider.load();
    }
    @Override
    public void execute(Socket socket, String par) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            provider.getByTitle(par).forEach(m -> {
                try {
                    bw.write(m.toString()+"\n");
                    bw.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            bw.write("stop\n");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
