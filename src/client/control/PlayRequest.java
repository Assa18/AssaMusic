package client.control;

import javax.sound.sampled.*;
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
            bw.write(0+"\n");
            bw.flush();
            bw.write("res/zene.wav\n");
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error with sending request!");
        }
    }

    @Override
    protected void recieve() {
        try {
            InputStream inputStream = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("recieved.wav");

            byte[] buffer = new byte[4096];
            int bytesRead;

            System.out.println("Recieving music..");
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("music got!");

            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        File audioFile = new File("recieved.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}
