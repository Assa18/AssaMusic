package client.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public abstract class MusicRequest {
    protected Socket socket;
    protected BufferedReader br;
    protected BufferedWriter bw;

    protected abstract void send(String msg);
    protected abstract void recieve();
}
