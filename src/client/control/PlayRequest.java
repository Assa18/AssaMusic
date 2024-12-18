package client.control;

import java.io.*;
import java.net.Socket;

public class PlayRequest extends MusicRequest{
    public PlayRequest() {
        try {
            this.socket = new Socket("localHost", 15000);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error with creating request!");
        }
    }
    @Override
    protected void send(String msg) {
        try {
            bw.write("Play request\n");
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error with sending request!");
        }
    }

    @Override
    protected void recieve() {

    }
}
