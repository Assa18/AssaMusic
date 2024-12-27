package server.command;

import java.net.Socket;

public interface RequestCommand {
    void execute(Socket socket, String par);
}
