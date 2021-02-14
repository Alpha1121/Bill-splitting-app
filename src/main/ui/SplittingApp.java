package ui;

import model.Product;
import model.User;

import java.util.Scanner;

public class SplittingApp {
    User user1;
    Product p1 = new Product();
    private Scanner in = new Scanner(System.in);

    public SplittingApp() {
        runApp();
    }

    private void runApp() {
        boolean on = true;
        String value = null;

        runDefault();

        while (on) {

            displayMenu();
            value = in.next();
            value = value.toLowerCase();

            if (value.equals("q")) {
                on = false;
                System.out.println("\nThank you for using this App \nGoodbye!");
            } else {
                processInput(value);
            }

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
        System.out.println("\tg -> Show final balance owed by all users");
        System.out.println("\tq -> to quit");

    }


    ;

    //add users
    private void addUser() {
        //adding username
        System.out.println("Please enter the name of user:");
        String input = in.next();
        User u = new User(input);
        user1.addUserToList(u);

    }

    //view users
    private void showUsers() {
        user1.getAllUsers();
    }

    //remove users
    private void removeUser() {
        System.out.println("Which user do you want to remove? \n enter their serial number");
        int input = in.nextInt();
        user1.removeUserFromList(input - 1);
    }

    //add products
    private void addItem() {

        System.out.println("Enter the product name");
        String itemName = in.next();


        System.out.println("Enter the cost ");
        float itemCost = in.nextFloat();

        System.out.println("If item is split between specific people only then press 1 "
                + "\n else press 0 to return to menu");
        int input = in.nextInt();
        if (input == 1) {
            split();
        }




    }

    //split between specific users
    private void split() {
        System.out.println("Enter the sr. numbers of people using this item");
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
