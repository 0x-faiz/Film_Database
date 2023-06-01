package Back_End;

import java.math.BigDecimal;
import GUI.*;

public class Film {

    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    private int runTime;

    public Film(String title, String director, String genre, int releaseYear, int runTime) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.runTime = runTime;
    }

    // Getter and setter methods

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}