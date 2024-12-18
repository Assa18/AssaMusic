package client;

import java.io.File;
import java.util.List;

public interface MusicEventListener {
    List<String> getSongNames(String searchString);
    File getSongSource(String name);
}
