package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {

    public String MenuOptions;

    public Menu() {

    }

    public String getMenu() {
        return MenuOptions = "Choose an option:\n" +
                "1 - Books List\n" +
                "2 - Checkout Book\n" +
                "3 - Return Book\n" +
                "0 - Quit\n" +
                "";
    }
}
