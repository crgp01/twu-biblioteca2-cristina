package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    @Mock
    private PrintStream printStream;
    @Mock
    private Biblioteca biblioteca;
    @Mock
    private Menu menu;
    @Mock
    private BufferedReader bufferedReader;

    private BibliotecaApp bibliotecaApp;
    @Mock
    private Movie movie;
    @Mock
    private Book book;
    @Mock
    private User user;

    @Before
    public void setUp() {

        bibliotecaApp = new BibliotecaApp(printStream, biblioteca, menu, bufferedReader);
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        try {
            bibliotecaApp.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(printStream).println(Message.WELCOME);
    }

    @Test
    public void shouldPrintMenu() {
        when(menu.getMenu()).thenReturn("menu");
        bibliotecaApp.run();
        verify(printStream).println(menu.getMenu());

    }

    @Test
    public void shouldPrintBookListWhenOptionIs1() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1");
        Book tituloPablo = new Book("Titulo", 2013, "Pablo", false, "1234");
        Book tituloAndrea = new Book("Titulo 2", 2013, "Andrea", false, "1234");
        ArrayList<Book> books = new ArrayList<>();
        books.add(tituloPablo);
        books.add(tituloAndrea);

        when(biblioteca.getBookList(anyBoolean())).thenReturn(books);

        bibliotecaApp.run();

        verify(printStream).println(tituloPablo);

    }

    @Test
    public void shouldNotPrintBookListWhenOptionIs1() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");
        when(biblioteca.getUserLogged()).thenReturn(new User("JuanPerez", "juan@perez.com", "0937342732", "111-1111", "1234", true));
        bibliotecaApp.run();
        verify(biblioteca, never()).getBookList(false);
    }

    @Test
    public void shouldPrintInvalidMenuOptionMessage() throws IOException {
        when(bufferedReader.readLine()).thenReturn("34");

        bibliotecaApp.run();

        verify(printStream).println(Message.INVALID_OPTION);
    }

    @Test
    public void shouldQuitMenu() throws IOException {
        when(bufferedReader.readLine()).thenReturn("0");

        bibliotecaApp.run();

        verify(bufferedReader).readLine();

    }

    @Test
    public void shouldCheckedBook() throws IOException {

        when(bufferedReader.readLine()).thenReturn("2");
        when(biblioteca.isBookCheckoutSuccessful(bufferedReader.readLine())).thenReturn(true);
        when(biblioteca.getUserLogged()).thenReturn(new User("JuanPerez", "juan@perez.com", "0937342732", "111-1111", "1234", true));
        bibliotecaApp.run();
        verify(printStream).println(Message.CHECKOUT_BOOK);

    }

    @Test
    public void shouldNotCheckedBook() throws IOException {

        when(bufferedReader.readLine()).thenReturn("2");
        when(biblioteca.isBookCheckoutSuccessful(bufferedReader.readLine())).thenReturn(false);
        when(biblioteca.getUserLogged()).thenReturn(new User("JuanPerez", "juan@perez.com", "0937342732", "111-1111", "1234", true));
        bibliotecaApp.run();

        verify(printStream).println(Message.UNSUCCESSFUL_BOOK_CHECKOUT);

    }

    @Test
    public void shouldReturnBook() throws IOException {

        when(bufferedReader.readLine()).thenReturn("3");
        when(biblioteca.returnBook(bufferedReader.readLine(), "1")).thenReturn(true);
        when(biblioteca.getUserLogged()).thenReturn(new User("JuanPerez", "juan@perez.com", "0937342732", "111-1111", "1234", true));
        bibliotecaApp.run();

        verify(printStream).println(Message.SUCCESSFUL_RETURN);

    }

    @Test
    public void shouldNotReturnBook() throws IOException {

        when(bufferedReader.readLine()).thenReturn("3");
        when(biblioteca.returnBook(bufferedReader.readLine(), "1")).thenReturn(false);
        when(biblioteca.getUserLogged()).thenReturn(new User("JuanPerez", "juan@perez.com", "0937342732", "111-1111", "1234", true));
        bibliotecaApp.run();

        verify(printStream).println(Message.UNSUCCESSFUL_RETURN);

    }

    @Test
    public void shouldShowListMovie() throws IOException {
        when(bufferedReader.readLine()).thenReturn("4");
        Movie goodMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        Movie averageMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(goodMovie);
        movies.add(averageMovie);

        when(biblioteca.getMovieList(anyBoolean())).thenReturn(movies);

        bibliotecaApp.run();

        verify(printStream).println(goodMovie);


    }

    @Test
    public void shouldFindMovieByName() {
        Movie goodMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        Movie averageMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(goodMovie);
        movies.add(averageMovie);

        assertNotEquals(-1, biblioteca.findMoviesByName(goodMovie.getName(), movies));

    }


    @Test
    public void shouldCheckOutAvailableMovie() throws IOException {
        when(bufferedReader.readLine()).thenReturn("5");
        Movie goodMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        Movie averageMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(goodMovie);
        movies.add(averageMovie);
        when(biblioteca.getUserLogged()).thenReturn(new User("JuanPerez", "juan@perez.com", "0937342732", "111-1111", "1234", true));
        when(biblioteca.isMovieCheckoutSuccessful(bufferedReader.readLine())).thenReturn(true);
        bibliotecaApp.run();

        verify(printStream).println(Message.CHECKOUT_MOVIE);

    }

    @Test
    public void shouldNotCheckedMovie() throws IOException {
        Movie awesomeMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        Movie veryGoodMovie = new Movie("Best Movie", 2015, "Awesome director", 10, false);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(awesomeMovie);
        movies.add(veryGoodMovie);

        when(biblioteca.getUserLogged()).thenReturn(new User("JuanPerez", "juan@perez.com", "0937342732", "111-1111", "1234", true));
        when(bufferedReader.readLine()).thenReturn("5");
        when(biblioteca.isMovieCheckoutSuccessful("Other movie")).thenReturn(false);
        bibliotecaApp.run();

        verify(printStream).println(Message.UNSUCCESSFUL_MOVIE_CHECKOUT);
    }


    @Test
    public void shouldNotPrintUserInformation() throws IOException {
      when(bufferedReader.readLine()).thenReturn("7");
        User usuario = new User();
        when(biblioteca.getUserLogged()).thenReturn(usuario);
        bibliotecaApp.run();
        verify(printStream).println(Message.LOGIN_REQUIRED);

    }

    @Test
    public void shouldPrintUserInformation() throws IOException {
        when(bufferedReader.readLine()).thenReturn("7");
        User usuario = new User("JuanPerez", "juan@perez.com", "0937342732", "111-1111", "1234", true);
        when(biblioteca.getUserLogged()).thenReturn(usuario);
        bibliotecaApp.run();
        verify(printStream).println(usuario.returnUserInformation());

    }


}