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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    @Mock
    private BibliotecaApp bibliotecaApp;
    @Mock
    private Movie movie;

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

        when(biblioteca.getListBook(anyBoolean())).thenReturn(books);

        bibliotecaApp.run();

        verify(printStream).println(tituloPablo);

    }

    @Test
    public void shouldNotPrintBookListWhenOptionIs1() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");
        bibliotecaApp.run();
        verify(biblioteca, never()).getListBook(false);
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
    public void shouldFindABookInBookList() {
        Book tituloPablo = new Book("Titulo", 2013, "Pablo", false, "1234");
        Book tituloAndrea = new Book("Titulo 2", 2013, "Andrea", false, "1234");
        ArrayList<Book> books = new ArrayList<>();
        books.add(tituloPablo);
        books.add(tituloAndrea);

        assertNotEquals(-1, biblioteca.findBooksByTitle(tituloAndrea.getTitle(), books));

    }

    @Test
    public void shouldCheckOutAvailableBook() throws IOException {
        Book tituloPablo = new Book("Titulo", 2013, "Pablo", false, "1234");
        Book tituloAndrea = new Book("Titulo 2", 2013, "Andrea", false, "1234");
        ArrayList<Book> books = new ArrayList<>();
        books.add(tituloPablo);
        books.add(tituloAndrea);

        when(bufferedReader.readLine()).thenReturn("2");
        when(biblioteca.checkoutBook(bufferedReader.readLine())).thenReturn(true);
        bibliotecaApp.run();

        verify(printStream).println(Message.CHECKOUT_BOOK);

    }

    @Test
    public void shouldNotCheckedBook() throws IOException {
        Book tituloPablo = new Book("Titulo", 2013, "Pablo", true, "1234");
        Book tituloAndrea = new Book("Titulo 2", 2013, "Andrea", false, "1234");
        ArrayList<Book> books = new ArrayList<>();
        books.add(tituloPablo);
        books.add(tituloAndrea);

        when(bufferedReader.readLine()).thenReturn("2");
        when(biblioteca.checkoutBook(bufferedReader.readLine())).thenReturn(false);
        bibliotecaApp.run();

        verify(printStream).println(Message.UNSUCCESSFUL_BOOK_CHECKOUT);

    }

    @Test
    public void shouldReturnBook() throws IOException {
        Book tituloPablo = new Book("Titulo", 2013, "Pablo", true, "1234");
        ArrayList<Book> books = new ArrayList<>();
        books.add(tituloPablo);

        when(bufferedReader.readLine()).thenReturn("3");
        when(biblioteca.returnBook(bufferedReader.readLine(), "1")).thenReturn(true);
        bibliotecaApp.run();

        verify(printStream).println(Message.SUCCESSFUL_RETURN);

    }

    @Test
    public void shouldNotReturnBook() throws IOException {

        Book tituloAndrea = new Book("Titulo 2", 2013, "Andrea", true, "1234");
        ArrayList<Book> books = new ArrayList<>();
        books.add(tituloAndrea);

        when(bufferedReader.readLine()).thenReturn("3");
        when(biblioteca.returnBook(bufferedReader.readLine(), "1")).thenReturn(false);
        bibliotecaApp.run();

        verify(printStream).println(Message.UNSUCCESSFUL_RETURN);

    }

    @Test
    public void shouldShowListMovie() throws IOException {
    when(bufferedReader.readLine()).thenReturn("4");
        Movie goodMovie = new Movie("Best Movie",2015,"Awesome director",10, false);
        Movie averageMovie = new Movie("Best Movie",2015,"Awesome director",10, false);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(goodMovie);
        movies.add(averageMovie);

        when(biblioteca.getListMovies(anyBoolean())).thenReturn(movies);

        bibliotecaApp.run();

        verify(printStream).println(goodMovie);


    }

    @Test
    public void shouldFindMovieByName() {
        Movie goodMovie = new Movie("Best Movie",2015,"Awesome director",10, false);
        Movie averageMovie = new Movie("Best Movie",2015,"Awesome director",10, false);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(goodMovie);
        movies.add(averageMovie);

        assertNotEquals(-1, biblioteca.findMoviesByName(goodMovie.getName(), movies));
        
    }

    @Test
    public void shouldCheckOutAvailableMovie() throws IOException {
        when(bufferedReader.readLine()).thenReturn("5");
        Movie goodMovie = new Movie("Best Movie",2015,"Awesome director",10, false);
        Movie averageMovie = new Movie("Best Movie",2015,"Awesome director",10, false);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(goodMovie);
        movies.add(averageMovie);

        when(biblioteca.checkoutMovie(bufferedReader.readLine())).thenReturn(true);
        bibliotecaApp.run();

        verify(printStream).println(Message.CHECKOUT_MOVIE);

    }

    @Test
    public void shouldNotCheckedMovie() throws IOException {
        Movie awesomeMovie = new Movie("Best Movie",2015,"Awesome director",10, false);
        Movie veryGoodMovie = new Movie("Best Movie",2015,"Awesome director",10, false);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(awesomeMovie);
        movies.add(veryGoodMovie);


        when(bufferedReader.readLine()).thenReturn("5");
        when(biblioteca.checkoutMovie("Other movie")).thenReturn(false);
        bibliotecaApp.run();

        verify(printStream).println(Message.UNSUCCESSFUL_MOVIE_CHECKOUT);
    }
}