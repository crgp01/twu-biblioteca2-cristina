package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Mock
    User user;

    @Mock
    Biblioteca biblioteca;

    @Mock
    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp(){

        user = new User();

    }

    @Test
    public void shouldReturnUserInformation() {

        user = new User("JuanPerez","juan@perez.com","0937342732","111-1111","1234",false);

        String userDetails = user.returnUserInformation();

        String whitespaceUserName = String.format("%-20s", "JuanPerez");
        String whitespaceEmail = String.format("%-20s", "juan@perez.com");
        String whitespacePhone = String.format("%-20s", "0937342732");


        String expected = String.format("%s | %s | %s\n", whitespaceUserName, whitespaceEmail, whitespacePhone);

        assertEquals(expected, userDetails);

    }

    @Test
    public void shouldVerifyUserCredentials() {
        User primerUsuario= new User("JuanPerez","juan@perez.com","0937342732","111-1111","1234",false);

        boolean isCorrectUser = primerUsuario.verifyUserCredentials(primerUsuario.getLibraryNumber(),primerUsuario.getPassword());

        assertTrue(isCorrectUser);

    }
}