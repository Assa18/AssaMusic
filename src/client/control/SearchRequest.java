package client.control;

import server.Music;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class SearchRequest extends MusicRequest{

    public SearchRequest() {
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
            bw.write("1\n");
            bw.flush();
            bw.write(msg+"\n");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Music> receive() {
        List<Music> tmp = new LinkedList<>();

        String line = new String();
        while (!line.equals("stop")) {
            try {
                line = br.readLine();
                if (line.equals("stop")) {
                    break;
                }
                String[] data = line.split(";");
                tmp.add(new Music(data[0], data[1], data[2],data[3]));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return tmp;
    }
}
