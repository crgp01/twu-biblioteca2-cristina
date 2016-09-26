package com.twu.biblioteca;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {
    @Mock
    Book book;

    @Before
    public void setUp() {

        book= new Book();
    }

    @Test
    public void shouldPrintbookDetailsTest() {

        book = new Book("It", 1987, "Stephen King", false, "1");

        String bookDetails = book.toString();

        String whitespaceTitle = String.format("%-20s", "It");
        String whitespaceAuthor = String.format("%-20s", "Stephen King");

        String expected = String.format("%s | %s | %s\n", whitespaceTitle, whitespaceAuthor, 1987);

        assertEquals(expected, bookDetails);
    }

}