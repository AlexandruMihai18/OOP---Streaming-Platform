package database;

import fileio.ContainsInput;

import java.util.ArrayList;

public final class Contains {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    public Contains(ContainsInput contains) {
        actors = new ArrayList<>(contains.getActors());
        genre = new ArrayList<>(contains.getGenre());
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }
}
