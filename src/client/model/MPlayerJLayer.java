package client.model;

import client.MusicEventListener;
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

    private MusicEventListener listener;


    public MPlayerJLayer() {
        new Thread(() -> {
            while (true) {
                if (player != null && player.isComplete()) {
                    System.out.println("The song is finished!");
                    listener.playNextSong();
                    //break;
                }
                if (!isPaused && player != null && fileInputStream != null){
                    listener.setPosition(getPosition());
                    System.out.println(getPosition()+"");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void load(File file) {
        source = file;
        if (playerThread != null) {
            if (player != null) {
                player.close();
            }
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
                isPaused = true;
                pauseLocation = totalLength - fileInputStream.available();
                player.close();
                if (playerThread != null) {
                    Thread tmp = playerThread;
                    playerThread = null;
                    tmp.interrupt();
                }
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

    public void setListener(MusicEventListener listener) {
        this.listener = listener;
    }

    @Override
    public float getPosition() {
        try {
            return (float)(totalLength-fileInputStream.available()) / (float)totalLength;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
