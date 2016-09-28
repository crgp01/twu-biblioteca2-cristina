package com.twu.biblioteca;

import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<User> users = new ArrayList<User>();
    private User userLogged;

    public User getUserLogged() {
        return userLogged;
    }

    public Biblioteca() {
        books = new ArrayList<Book>() {{
            add(new Book("Metamorfosis", 1960, "Franz Kafka", false, "1"));
            add(new Book("It", 1987, "Stephen King", false, "1"));
            add(new Book("Perfume", 1960, "Varios", true, "1"));
        }};
        movies = new ArrayList<Movie>() {{
            add(new Movie("Fight Club", 1999, "David Fincher", 8, false));
            add(new Movie("Forrest Gump", 1994, "Robert Zemeckis", 8, false));
            add(new Movie("Star wars", 1977, "George Lucas", 9, true));
        }};
        users = new ArrayList<User>() {{
            add(new User("JuanPerez", "juan@perez.com", "0937342732", "111-1111", "1234", false));
            add(new User("MariaLopez", "maria@lopez.com", "0985845345", "111-1111", "1234", false));
            add(new User("AndresParedes", "andres@paredes.com", "0937342732", "222-2222", "1234", false));

        }};

    }


    public ArrayList<Book> getBookList(boolean condition) {
        ArrayList<Book> bookList = new ArrayList<Book>();

        for (Book book : books) {
            if (book.isCheckedOut() == condition) {
                bookList.add(book);
            }
        }
        return bookList;
    }

    public int findBooksByTitle(String bookTitle, ArrayList<Book> books) {
        int i = 0;
        int position = -1;

        for (Book book : books) {
            if (book.getTitle().equals(bookTitle)) {
                position = i;
                break;
            }
            i++;
        }

        return position;
    }

    public boolean isBookCheckoutSuccessful(String bookTitle) {
        ArrayList<Book> availableBooks = getBookList(false);
        int position = findBooksByTitle(bookTitle, availableBooks);

        if (position >= 0) {

            availableBooks.get(position).setCheckedOut(true);
            return true;
        } else {
            return false;
        }
    }

    public boolean returnBook(String bookTitle, String libraryNumber) {
        ArrayList<Book> checkOutBooks = getBookList(true);

        int position = findBooksByTitle(bookTitle, checkOutBooks);

        if (position >= 0) {

            if (libraryNumber.equals(checkOutBooks.get(position).getLibraryNumber())) {
                checkOutBooks.get(position).setCheckedOut(false);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<Movie> getMovieList(boolean condition) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        for (Movie movie : movies) {
            if (movie.isCheckedOut() == condition) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    public int findMoviesByName(String movieTitle, ArrayList<Movie> movies) {
        int i = 0;
        int position = -1;

        for (Movie movie : movies) {
            if (movie.getName().equals(movieTitle)) {
                position = i;
                break;
            }
            i++;
        }

        return position;
    }

    public boolean isMovieCheckoutSuccessful(String movieName) {
        ArrayList<Movie> availableMovies = getMovieList(false);
        int position = findMoviesByName(movieName, availableMovies);

        if (position >= 0) {
            availableMovies.get(position).setCheckedOut(true);
            return true;
        } else {
            return false;
        }
    }

    public int findUserByLibraryNameAndPassword(String libraryNumber, String password) {
        int i = 0;
        int position = -1;

        for (User user : users) {
            if (user.verifyUserCredentials(libraryNumber, password)) {
                position = i;
                break;
            }
            i++;
        }
        return position;
    }

    public boolean loginUser(String libraryNumber, String password) {
        int position = findUserByLibraryNameAndPassword(libraryNumber, password);
        if (position != - 1) {
            users.get(position).setLogged(true);
            userLogged = users.get(position);
            return true;
        }
        return false;
    }

}


