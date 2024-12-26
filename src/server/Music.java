package server;

import java.util.Objects;
import java.util.UUID;

public class Music {
    private String title;
    private String author;
    private String path;
    private String id;

    public Music(String id, String title, String author, String path) {
        this.author = author;
        this.path = path;
        this.title = title;
        this.id = id;
    }

    public Music() {
        this.author = "";
        this.path = "";
        this.title = "";
        this.id = "";
    }

    public String getId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music that = (Music) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return id + ";" + title + ";" + author + ";" + path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
