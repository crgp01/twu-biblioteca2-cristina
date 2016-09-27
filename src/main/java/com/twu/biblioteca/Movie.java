package com.twu.biblioteca;

import java.sql.Timestamp;

public class Movie {

    private String name;
    private int year;
    private String director;
    private int movieRating;
    private boolean isCheckedOut =false;

    public Movie(String name, int year, String director, int movieRating, boolean isCheckedOut ) {

        this.name = name;
        this.year = year;
        this.director = director;
        this.movieRating = movieRating;
        this.isCheckedOut = isCheckedOut;
    }
    public Movie() {

    }

    public String toString() {
        String whitespaceName = String.format("%-20s", name);
        String whitespaceDirector = String.format("%-20s", director);
        String whitespaceYear = String.format("%-20s", year);
        String whitespaceRating = String.format("%-20s", movieRating);


        return String.format("%s | %s | %s\n",
                whitespaceName,
                whitespaceDirector,
                whitespaceYear, whitespaceRating);
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }
    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getName() {
        return name;
    }
}



