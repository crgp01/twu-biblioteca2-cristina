package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuTest {

    @Mock
    private Menu menu;
    @Mock
    private BibliotecaApp bibliotecaApp;
    @Before
    public void setUp() {

        menu = new Menu();
    }

    @Test
    public void shouldGetMenu() {

       String menuExpected = menu.getMenu();
        String menuActual = menu.MenuOptions;
        assertEquals(menuExpected,menuActual);
    }

}