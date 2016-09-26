package com.twu.biblioteca;

import java.sql.Timestamp;

public class Movie {

    private String name;
    private int year;
    private String director;
    public int movieRating;
    public  boolean isCheckedOut =false;



    public Movie(String name, int year, String director, int movieRating, boolean isChecked ) {

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


        return String.format("%s | %s | %s\n",
                whitespaceName,
                whitespaceDirector,
                whitespaceYear, movieRating);
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



