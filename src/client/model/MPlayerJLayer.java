package client.model;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;

public class MPlayerJLayer implements MusicPlayer {
    private File source;
    private Player player;
    private Thread playerThread = null;

    private boolean isPaused = false;
    private long pauseLocation = 0;
    private long totalLength = 0;
    private FileInputStream fileInputStream;

    @Override
    public void load(File file) {
        source = file;
        if (playerThread != null) {
            Thread tmp = playerThread;
            playerThread = null;
            tmp.interrupt();
        }
        try {
            fileInputStream = new FileInputStream(source);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            totalLength = fileInputStream.available();

            player= new Player(fileInputStream);

            playerThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (JavaLayerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() {
        playerThread.start();
    }

    @Override
    public void stop() {
        if (player != null) {
            try {
                pauseLocation = totalLength - fileInputStream.available();
                player.close();
                if (playerThread != null) {
                    Thread tmp = playerThread;
                    playerThread = null;
                    tmp.interrupt();
                }
                isPaused = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void resume() {
        isPaused = false;
        try {
            fileInputStream = new FileInputStream(source);
            fileInputStream.skip(pauseLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            player = new Player(fileInputStream);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
        playerThread = new Thread(() -> {
            try {
                player.play();
            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }
        });
        playerThread.start();
    }

    @Override
    public boolean isRunning() {
        return !isPaused;
    }
}
