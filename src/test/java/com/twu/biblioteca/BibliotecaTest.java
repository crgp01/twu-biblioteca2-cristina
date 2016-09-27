package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class BibliotecaTest {

    private Biblioteca biblioteca;
    public Menu menu;
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private PrintStream printStream;


    @Before
    public void setUp() {
        biblioteca = new Biblioteca();

    }

    @Test
    public void shouldFindABookInBookList() {
        Book tituloPablo = new Book("Titulo", 2013, "Pablo", false, "1234");
        Book tituloAndrea = new Book("Titulo 2", 2013, "Andrea", false, "1234");
        ArrayList<Book> books = new ArrayList<>();
        books.add(tituloPablo);
        books.add(tituloAndrea);

        assertNotEquals(-1, biblioteca.findBooksByTitle(tituloAndrea.getTitle(), books));

    }

    @Test
    public void shouldNotReturnPositionWhenABookIsNotFoundByName() {
        ArrayList<Book> books = new ArrayList<>();

        assertEquals(-1, biblioteca.findBooksByTitle("", books));

    }

    @Test
    public void shouldCheckOutAvailableBook() {

        boolean isCheckOut = biblioteca.isBookCheckoutSuccessful("It");
        assertTrue(isCheckOut);
    }

    @Test
    public void shouldNotCheckOutNotAvailableBook() {
        boolean isCheckOut = biblioteca.isBookCheckoutSuccessful("fdsfds");
        assertFalse(isCheckOut);
    }

    @Test
    public void shouldReturnBook() {
        boolean isReturned = biblioteca.returnBook("Perfume", "1");
        assertTrue(isReturned);

    }

    @Test
    public void shouldNotReturnBook() {
        boolean isReturned = biblioteca.returnBook("dsdsf", "1");
        assertFalse(isReturned);

    }

    @Test
    public void shouldGetMovieAvailableList() {
        ArrayList<Movie> movieList = biblioteca.getMovieList(false);
        assertNotNull(movieList);
    }

    @Test
    public void shouldReturnMoviePositionWhenNameIsFound() {
        ArrayList<Movie> movies = new ArrayList<>();
        Movie goodMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        Movie averageMovie = new Movie("Average Movie", 2015, "Good director", 6, false);
        movies.add(goodMovie);
        movies.add(averageMovie);


        assertEquals(-1, biblioteca.findMoviesByName("Other movie", movies));

    }

    @Test
    public void shouldReturnMoviePositionWhenListIsNull() {
        ArrayList<Movie> movies = new ArrayList<>();
        Movie goodMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        Movie averageMovie = new Movie("Average Movie", 2015, "Good director", 6, false);
        movies.add(goodMovie);
        movies.add(averageMovie);

        assertNotEquals(-1, biblioteca.findMoviesByName(goodMovie.getName(), movies));

    }

    @Test
    public void shouldCheckOutAvailableMovie() {
        boolean isCheckOut = biblioteca.isMovieCheckoutSuccessful("Fight Club");
        assertTrue(isCheckOut);
    }

    @Test
    public void shouldNotCheckOutMovie() {
        boolean isCheckOut = biblioteca.isMovieCheckoutSuccessful("fsfdf");
        assertFalse(isCheckOut);
    }

    @Test
    public void shouldVerifyUserCredentials() {
        User primerUsuario = new User("PrimerUsuario", "usuario@1.com", "0937342732", "111-1111", "1234", false);
        int userPosition = biblioteca.findUserByLibraryNameAndPassword(primerUsuario.getLibraryNumber(), primerUsuario.getPassword());
        assertNotEquals(-1, userPosition);
    }

    @Test
    public void shouldLogInAUser() {
        String libraryNumber = "111-1111";
        String password = "1234";
        assertTrue(biblioteca.loginUser(libraryNumber, password));
    }

    @Test
    public void shouldNotLoginNotExistingUser() {
        String libraryNumber = "000-0000";
        String password = "xxxx";
        assertFalse(biblioteca.loginUser(libraryNumber, password));

    }

    @Test
    public void shouldReturnIfUserIsLogged() {
            String libraryNumber = "111-1111";
            String password = "1234";

            boolean isUserLogged = biblioteca.loginUser(libraryNumber,password);
            assertTrue(isUserLogged);

        }

}