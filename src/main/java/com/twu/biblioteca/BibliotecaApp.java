package com.twu.biblioteca;

import com.twu.biblioteca.Biblioteca;
import com.twu.biblioteca.Message;

import java.awt.*;
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

        menuOptions();
    }

    public void menuOptions() {

        try {
            String option = bufferedReader.readLine();
            option = option == null ? "" : option;
            switch (option) {
                case "0":
                    bufferedReader = null;
                    break;

                case "1":

                    ArrayList<Book> books = biblioteca.getListBook(false);
                    for (Book book : books) {
                        printStream.println(book);
                    }

                    break;

                case "2":
                    String title = bufferedReader.readLine();

                    if (biblioteca.checkoutBook(title)) {
                        printStream.println(Message.CHECKOUT_BOOK);
                    } else {

                        printStream.println(Message.UNSUCCESSFUL_BOOK_CHECKOUT);

                    }
                    break;
                case "3":
                    if (biblioteca.returnBook(bufferedReader.readLine(), "1")) {
                        printStream.println(Message.SUCCESSFUL_RETURN);
                    } else {
                        printStream.println(Message.UNSUCCESSFUL_RETURN);
                    }
                    break;

                case "4":
                    ArrayList<Movie> movies = biblioteca.getListMovies(false);
                    for (Movie movie : movies) {
                        printStream.println(movie);
                    }

                case "5":
                    String name = bufferedReader.readLine();

                    if (biblioteca.checkoutMovie(name)) {
                        printStream.println(Message.CHECKOUT_MOVIE);
                    } else {

                        printStream.println(Message.UNSUCCESSFUL_MOVIE_CHECKOUT);

                    }
                    break;

                default:
                    printStream.println(Message.INVALID_OPTION);
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(System.out, new Biblioteca(), new Menu(), new BufferedReader(new InputStreamReader(System.in)));
        bibliotecaApp.run();
    }

}
