package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    @Mock
    Movie movie;

    @Before
    public void setUp() {

        movie= new Movie();
    }

    @Test
    public void shouldPrintbookDetailsTest() {

        movie = new Movie("Best Movie",2015,"Awesome director",10, false);

        String movieDetails = movie.toString();

        String whitespaceName = String.format("%-20s", "Best Movie");
        String whitespaceDirector = String.format("%-20s", "Awesome director");
        String whitespaceYear = String.format("%-20s", 2015);
        String whitespaceRating = String.format("%-20s", 10);


        String expected = String.format("%s | %s | %s\n", whitespaceName, whitespaceDirector, whitespaceYear, whitespaceRating);


        assertEquals(expected, movieDetails);
    }



}