package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class BibliotecaApp {

    private Biblioteca biblioteca;
    private Menu menu;
    private BufferedReader bufferedReader;
    private PrintStream printStream;


    public BibliotecaApp(PrintStream printStream, Biblioteca biblioteca, Menu menu, BufferedReader bufferedReader) {
        this.printStream = printStream;
        this.biblioteca = biblioteca;
        this.menu = menu;
        this.bufferedReader = bufferedReader;

    }

    public void run() {
        printStream.println(Message.WELCOME);
        printStream.println(menu.getMenu());
        String option = "";

        option = readOptions();
        menuOptions(option);

    }

    private void menuOptions(String option) {
        option = option == null ? "" : option;

            try {
                    switch (option) {
                        case "0":
                            break;

                        case "1":
                            printBookList();
                            break;

                        case "2":
                            checkOutBook();
                            break;
                        case "3":
                            returnBook();
                            break;

                        case "4":
                            printMovieList();
                            break;
                        case "5":
                            checkOutMovie();
                            break;

                        default:
                            printInvalidOption();
                            break;
                    }

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    private String readOptions() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void printInvalidOption() {
        printStream.println(Message.INVALID_OPTION);
    }

    private void returnBook() throws IOException {

            if (biblioteca.returnBook(bufferedReader.readLine(), "1")) {
                printStream.println(Message.SUCCESSFUL_RETURN);
            } else {
                printStream.println(Message.UNSUCCESSFUL_RETURN);
            }

    }

    private void checkOutBook() throws IOException {
        String title;
            title = bufferedReader.readLine();

        if (biblioteca.isBookCheckoutSuccessful(title)) {
            printStream.println(Message.CHECKOUT_BOOK);
        } else {

            printStream.println(Message.UNSUCCESSFUL_BOOK_CHECKOUT);
        }
    }

    private void printBookList() {
        ArrayList<Book> books = biblioteca.getBookList(false);
        for (Book book : books) {
            printStream.println(book);
        }
    }

    private void checkOutMovie() throws IOException {
        String name;

        name = bufferedReader.readLine();


        if (biblioteca.isMovieCheckoutSuccessful(name)) {
            printStream.println(Message.CHECKOUT_MOVIE);
        } else {

            printStream.println(Message.UNSUCCESSFUL_MOVIE_CHECKOUT);

        }
    }

    private void printMovieList() {
        ArrayList<Movie> movies = biblioteca.getMovieList(false);
        for (Movie movie : movies) {
            printStream.println(movie);
        }
    }


    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(System.out, new Biblioteca(), new Menu(), new BufferedReader(new InputStreamReader(System.in)));
        bibliotecaApp.run();
    }


}
