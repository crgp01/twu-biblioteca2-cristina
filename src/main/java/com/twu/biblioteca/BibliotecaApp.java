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
       while (!option.equals("0")) {
            option = readOptions();

            menuOptions(option);
        }
    }

    private void menuOptions(String option) {
        option = option == null ? "" : option;

        try {
            switch (option) {
                case "0":
                    bufferedReader.close();
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
                case "6":
                    verifyUserCredentials();
                case "7":
                    printUserInformation();
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

    private void printBookList() {
        ArrayList<Book> books = biblioteca.getBookList(false);
        for (Book book : books) {
            printStream.println(book);
        }
    }

    private void checkOutBook() throws IOException {
        if (!hasUserLogin()) {
            printStream.println(Message.LOGIN_REQUIRED);
        } else {
            printStream.println("Book name:");
            String title = bufferedReader.readLine();

            if (biblioteca.isBookCheckoutSuccessful(title)) {
                printStream.println(Message.CHECKOUT_BOOK);
            } else {

                printStream.println(Message.UNSUCCESSFUL_BOOK_CHECKOUT);
            }
        }
    }

    private boolean hasUserLogin() {
        return biblioteca.getUserLogged()!= null && biblioteca.getUserLogged().isLogged();
    }

    private void returnBook() throws IOException {
        if (!hasUserLogin()) {
            printStream.println(Message.LOGIN_REQUIRED);
        } else {
            printStream.println("Book name:");

            if (biblioteca.returnBook(bufferedReader.readLine(), "1")) {
                printStream.println(Message.SUCCESSFUL_RETURN);
            } else {
                printStream.println(Message.UNSUCCESSFUL_RETURN);
            }
        }
    }

    private void printMovieList() {
        ArrayList<Movie> movies = biblioteca.getMovieList(false);
        for (Movie movie : movies) {
            printStream.println(movie);
        }
    }

    private void checkOutMovie() throws IOException {
        if (!hasUserLogin()) {
            printStream.println(Message.LOGIN_REQUIRED);
        } else {
            printStream.println("Movie name:");
            String name = bufferedReader.readLine();

            if (biblioteca.isMovieCheckoutSuccessful(name)) {
                printStream.println(Message.CHECKOUT_MOVIE);
            } else {

                printStream.println(Message.UNSUCCESSFUL_MOVIE_CHECKOUT);

            }
        }
    }

    public boolean verifyUserCredentials() throws IOException {
        printStream.println("Library number:");
        String libraryNumber = bufferedReader.readLine();

        printStream.println("Password:");
        String password = bufferedReader.readLine();

        boolean userIsLogged = biblioteca.loginUser(libraryNumber, password);

        return isLoginSuccessful(userIsLogged);

    }

    public boolean isLoginSuccessful(boolean userIsLogged) {
        if (userIsLogged) {
            printStream.println(Message.LOGIN_SUCCESSFUL);
            return true;
        } else {
            printStream.println(Message.LOGIN_UNSUCCESSFUL);
            return false;
        }
    }

    private void printUserInformation() {

        if (hasUserLogin()) {
            printStream.println(biblioteca.getUserLogged().returnUserInformation());
        } else {
            printStream.println(Message.LOGIN_REQUIRED);
        }
    }


    private void printInvalidOption() {
        printStream.println(Message.INVALID_OPTION);
    }


    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(System.out, new Biblioteca(), new Menu(), new BufferedReader(new InputStreamReader(System.in)));
        bibliotecaApp.run();
    }


}
