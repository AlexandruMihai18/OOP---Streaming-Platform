package database;

import fileio.SortInput;

public final class Sort {
    private String rating;
    private String duration;

    public Sort(SortInput sort) {
        rating = sort.getRating();
        duration = sort.getDuration();
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
