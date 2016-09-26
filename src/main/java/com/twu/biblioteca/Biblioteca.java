package com.twu.biblioteca;
import java.util.ArrayList;
public class Biblioteca {

    public ArrayList<Book> books = new ArrayList<Book>();
    public ArrayList<Movie> movies = new ArrayList<Movie>();

    public Biblioteca() {
        books = new ArrayList<Book>() {{
            add(new Book("Metamorfosis",1960 ,"Franz Kafka", false,"1"));
            add(new Book("It",1987 ,"Stephen King", false, "1"));
            add(new Book("Perfume",1960 ,"Varios", true, "1"));
        }};
        movies = new ArrayList<Movie>() {{
            add(new Movie("Fight Club",1999,"David Fincher",8,false));
            add(new Movie("Forrest Gump",1994 ,"Robert Zemeckis", 8,false));
            add(new Movie("Star wars",1977 ,"George Lucas", 9, true));
        }};
    }

    public ArrayList<Book> getListBook(boolean condition) {
        ArrayList<Book> bookList = new ArrayList<Book>();

        for (Book book : books) {
            if (book.isCheckedOut()==condition) {
                bookList.add(book);
            }
        }
        return bookList;
    }


    public int findBooksByTitle(String bookTitle, ArrayList<Book> books){
        int i = 0;
        int position = -1;

        for (Book book : books){
            if(book.getTitle().equals(bookTitle)){
                position = i;
                break;
            }
            i++;
        }

        return position;
    }

    public boolean checkoutBook(String bookTitle) {
        ArrayList<Book> availableBooks = getListBook(false);
        int position = findBooksByTitle(bookTitle, availableBooks);

        if (position >= 0) {

            availableBooks.get(position).setCheckedOut(true);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean returnBook(String bookTitle, String libraryNumber) {
        ArrayList<Book> checkOutBooks = getListBook(true);

        int position = findBooksByTitle(bookTitle, checkOutBooks);

        if (position >= 0) {

            if(libraryNumber.equals(checkOutBooks.get(position).getLibraryNumber())) {
                checkOutBooks.get(position).setCheckedOut(false);
                return true;
            }
            else
            {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public ArrayList<Movie> getListMovies(boolean condition) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        for (Movie movie : movies) {
            if (movie.isCheckedOut()==condition) {
                movieList.add(movie);
            }
        }
        return movieList;
    }
    public int findMoviesByName(String movieTitle, ArrayList<Movie> movies){
        int i = 0;
        int position = -1;

        for (Movie movie : movies){
            if(movie.getName().equals(movieTitle)){
                position = i;
                break;
            }
            i++;
        }

        return position;
    }

    public boolean checkoutMovie(String movieName) {
        ArrayList<Movie> availableMovies = getListMovies(false);
        int position = findMoviesByName(movieName, availableMovies);

        if (position >= 0) {
            availableMovies.get(position).setCheckedOut(true);
            return true;
        }
        else {
            return false;
        }
    }



}


