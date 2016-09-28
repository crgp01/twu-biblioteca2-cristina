package com.twu.biblioteca;

public class Menu {

    public String MenuOptions;

    public Menu() {

    }
    public String getMenu() {
        return MenuOptions = "Choose an option:\n" +
                "1 - Books List\n" +
                "2 - Checkout Book\n" +
                "3 - Return Book\n" +
                "4 - Movie List\n" +
                "5 - Checkout Movie\n" +
                "6 - Log In\n" +
                "7 - User Information\n" +
                "0 - Quit\n" +
                "";
    }
}
