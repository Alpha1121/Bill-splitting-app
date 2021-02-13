package ui;

import model.User;

import java.util.Scanner;

public class SplittingApp {
    User user1;


    private Scanner input;

    public SplittingApp() {
        runApp();
    }

    private void runApp() {
        boolean on = true;
        String in;

        runDefault();

        while (on) {
            displayMenu();

            in = input.next();
            System.out.println("TEST");
            in = in.toLowerCase();
            if (input.equals("q")) {
                on = false;
            } else {
                processInput(in);
            }

            System.out.println("\nThank you for using this App \nGoodbye!");

        }

    }

    private void runDefault() {
        user1 = new User("You");
        user1.addUserToList(user1);
    }

    private void processInput(String input) {
        if (input.equals("a")) {
            addUser();
        } else if (input.equals("b")) {
            showUsers();
        } else if (input.equals("c")) {
            removeUser();
        } else if (input.equals("d")) {
            addItem();
        } else if (input.equals("e")) {
            showItems();
        } else if (input.equals("f")) {
            removeItem();
        } else if (input.equals("g")) {
            finalBalance();
        } else {
            System.out.println("Selection not valid...");
        }
    }



    private void displayMenu() {
        System.out.println("\nHI!! what would you like to do?\n please select:");
        System.out.println("\ta -> add new user");
        System.out.println("\tb -> Show all users");
        System.out.println("\tc -> Remove an existing user");
        System.out.println("\td -> add an item");
        System.out.println("\te -> view all items");
        System.out.println("\tf -> Remove an item");
        System.out.println("\tg -> Show final balance of all users");
        System.out.println("\tq -> to quit");

    }


    ;

    //add users
    private void addUser() {
        //adding username
        System.out.println("Please enter the name of user:");
        String in = input.next();
        User u;
        u = new User(in);
        u.addUserToList(u);

    }

    //view users
    private void showUsers() {
        user1.getAllUsers();

    }

    //remove users
    private void removeUser() {
    }

    //add products
    private void addItem() {

        //split between specific users
    }

    //view products
    private void showItems() {
    }

    //remove products
    private void removeItem() {
    }



    //show total balance of all users
    private void finalBalance() {
    }

}
