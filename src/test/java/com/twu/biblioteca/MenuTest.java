package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MenuTest {

    @Mock
    private Menu menu;

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