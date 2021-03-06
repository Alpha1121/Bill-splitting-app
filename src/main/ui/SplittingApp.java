package ui;

import model.Product;
import model.ProductsList;
import model.User;
import model.UsersList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SplittingApp {

    User user1;
    UsersList usersList;
    ProductsList productsList;
    private static Scanner in = new Scanner(System.in);

    public SplittingApp() {
        runApp();
    }

    private void runApp() {
        boolean on = true;
        String input = null;

        runDefault();

        while (on) {

            displayMenu();
            input = in.next();
            input = input.toLowerCase();

            if (input.equals("q")) {
                on = false;
                System.out.println("\nThank you for using this App \nGoodbye!");
            } else {
                processInput(input);
            }

        }

    }

    private void runDefault() {
        usersList = new UsersList();
        productsList = new ProductsList();

        user1 = new User("You");
        usersList.addUserToList(user1);
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
        usersList.addUserToList(u);

    }

    //view users
    private void showUsers() {
        usersList.getAllUsers();
    }

    //remove users
    private void removeUser() {
        System.out.println("Which user do you want to remove? \n enter their serial number");
        showUsers();
        int input = in.nextInt();
        usersList.removeUserFromList(input - 1);
    }

    //add products
    private void addItem() {
        Product p = new Product();

        System.out.println("Enter the product name");
        String itemName = in.next();
        p.setName(itemName);

        System.out.println("Enter the cost ");
        float itemCost = in.nextFloat();
        p.setCost(itemCost);

        productsList.addProductToList(p);
        
        System.out.println("If item is split between specific people only then press 1 "
                + "\n else press anything else to return to menu");
        int input = in.nextInt();
        if (input == 1) {
            splitBetween(p);
        }

        split(usersList.getSize(), p);

    }

    //split between specific users
    private void splitBetween(Product p) {
        System.out.println("Enter the number of people using this item");
        int num = in.nextInt();
        split(num, p);


    }

    private void split(int num, Product p) {
        List<Integer> x = new ArrayList<Integer>();

        for (int i = 0; i < num; i++) {
            System.out.println("Enter the sr. number of the person using this item");
            showUsers();
            int y = in.nextInt();
            x.add(y);
        }

        float splitCost = p.getCost() / num;


        for (int i = 0; i < x.size(); i++) {
            User u = usersList.getUser(x.get(i));
            u.addBalance(splitCost);
            p.setUsers(u);
        }

    }

    //view products
    private void showItems() {
        productsList.getAllProducts();

    }

    //remove products
    private void removeItem() {
        System.out.println("choose product to remove");
        showItems();
        int i = in.nextInt();
        productsList.removeProductFromList(productsList.getProduct(i - 1));

    }




    //show total balance of all users
    private void finalBalance() {
        System.out.println("Total cost = " + productsList.getTotalBalance());
        showUsers();
    }

}
