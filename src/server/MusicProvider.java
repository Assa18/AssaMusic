package server;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MusicProvider {
    private Map<String, Music> musics;

    private boolean loaded = false;

    private static MusicProvider instance;

    public synchronized static MusicProvider getInstance() {
        if (instance == null) {
            instance = new MusicProvider();
        }
        return instance;
    }

    private MusicProvider() {
        this.musics = new HashMap<>();
    }

    public synchronized void load() {
        if (loaded) return;

        if (instance != null) {
            instance.musics.clear();
        }
        else {
            instance = getInstance();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("res/input.txt"));

            instance.musics = br.lines().map(l-> {
                String[] data = l.split(";");
                Music m = new Music(data[0],data[1], data[2]);
                return m;
            }).collect(Collectors.toMap(Music::getId,m->m));

            br.close();
        } catch (IOException e) {
            System.out.println("ERROR: input file not found!");
        }
        loaded = true;
    }

    public synchronized void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("res/input.txt"));

            musics.forEach((k,m) ->{
                String line = m.getTitle()+";"+m.getPath()+";"+m.getAuthor()+"\n";
                try {
                    bw.write(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Music> getByTitle(String title) {
        return null;
    }

    public Music getById(String id) {
        return musics.get(id);
    }

    public String getPath(String id) {
        return musics.get(id).getPath();
    }
}
