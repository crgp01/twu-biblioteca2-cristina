package com.twu.biblioteca;

public class Book {

    private String title;
    private int year;
    private String author;
    private String libraryNumber;
    private boolean isCheckedOut = false;


    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public Book(String title, int year, String author, boolean isCheckedOut, String libraryNumber) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.isCheckedOut = isCheckedOut;

        this.libraryNumber = libraryNumber;
    }
    public Book() {

    }

    public String toString() {
        String whitespaceTitle = String.format("%-20s", title);
        String whitespaceAuthor = String.format("%-20s", author);

        return String.format("%s | %s | %s\n",
                whitespaceTitle,
                whitespaceAuthor,
                year);
    }
}
