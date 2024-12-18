package server;

import java.net.Socket;

public interface RequestCommand {
    void execute(Socket socket);
}
