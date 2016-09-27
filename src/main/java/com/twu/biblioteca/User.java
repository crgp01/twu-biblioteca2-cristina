package com.twu.biblioteca;

public class User {
    private String userName;
    private String email;
    private String phone;
    private String libraryNumber;
    private String password;
    private boolean isLogged;

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLogged() {
        return isLogged;
    }
    public void setLogged(boolean logged) {
        isLogged = logged;
    }



    public User(String userName, String email, String phone, String libraryNumber, String password, boolean isLogged) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.isLogged = isLogged;
    }

    public User() {

    }

    public String returnUserInformation(){
        String whitespaceUserName = String.format("%-20s", userName);
        String whitespaceEmail = String.format("%-20s", email);
        String whitespacePhone = String.format("%-20s", phone);

        return String.format("%s | %s | %s\n",
                whitespaceUserName,
                whitespaceEmail,
                whitespacePhone);

    }

    public boolean verifyUserCredentials(String libraryNumberInput, String passwordInput){
        return libraryNumberInput.equals(libraryNumber)&&passwordInput.equals(password);
    }
}
