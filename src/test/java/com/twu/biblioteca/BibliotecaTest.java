package com.twu.biblioteca;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaTest {

    private Biblioteca biblioteca;
    public Menu menu;

    @Before
    public void setUp() {
        biblioteca = new Biblioteca();
        menu = new Menu();
    }

    @Ignore
    public void shouldReturnListOfAvailableBooks() {
        ArrayList<Book> expectedBookList = new ArrayList<Book>() {{
            add(new Book("Metamorfosis",1960 ,"Franz Kafka", false,"1"));
            add(new Book("It",1987 ,"Stephen King", false, "1"));
        }};

        ArrayList<Book> bookList = biblioteca.getListBook(false);

        assertEquals(expectedBookList, bookList);
    }

    @Test

    public void booksAvailableCheckout() {

        ArrayList<Book> bookList = biblioteca.getListBook(false);

        assertEquals(biblioteca.getListBook(false), bookList);
    }

    @Test

    public void booksCheckedOut() {

        ArrayList<Book> bookList = biblioteca.getListBook(true);

        assertEquals(biblioteca.getListBook(true), bookList);
    }

    @Test
    public void shouldFindAvailableBook() {
        int position = 0;
        String bookTitle = "It";
        ArrayList<Book> bookList = biblioteca.getListBook(false);
        position = biblioteca.findBooksByTitle(bookTitle, bookList);

        assertEquals(1, position);
    }

    @Test
    public void shouldCheckOutBook() {

        String bookTitle = "It";
        boolean isCheckOut = biblioteca.checkoutBook(bookTitle);
        assertEquals(true, isCheckOut);
    }

    @Test
    public void shouldNotCheckOutBook() {

        String bookTitle = "hgh";
        boolean isNotCheckOut = biblioteca.checkoutBook(bookTitle);

        assertEquals(false, isNotCheckOut);
    }

    @Test
    public void shouldReturnBook() {

        String bookTitle = "Perfume";
        String libraryNumber = "1";

        boolean isReturned = biblioteca.returnBook(bookTitle, libraryNumber);

        assertEquals(true, isReturned);

    }

    @Test
    public void shouldReturnInvalidBook() {

        String bookTitle = "Other book";
        String libraryNumber = "2";

        boolean isNotReturned = biblioteca.returnBook(bookTitle, libraryNumber);

        assertEquals(false, isNotReturned);
    }

    @Test
    public void shouldFindAvailableMovie() {
    int position=-1;

        String movieName = "Fight Club";
        ArrayList<Movie> movieList = biblioteca.getListMovies(false);
        int expectedPosition = biblioteca.findMoviesByName(movieName,movieList);

        assertNotEquals(expectedPosition,position);

    }

    @Test
    public void shouldCheckOutMovie() {
        String movieName = "Fight Club";

        boolean wasCheckedOut = biblioteca.checkoutMovie(movieName);

        assertEquals(true,wasCheckedOut);

    }


}