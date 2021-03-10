package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SplittingApp {
    private static final String JSON_STORE = "./data/SplittingApp.json";
    User user1;
    UsersList usersList;
    ProductsList productsList;
    Bill bill;
    private static Scanner in = new Scanner(System.in);

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public SplittingApp() throws FileNotFoundException {
        runApp();
    }

    private void runApp() {
        boolean on = true;
        String input;

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

    //EFFECT instantiates the userList and productList, also adding a default user in the usersList.
    private void runDefault() {
        usersList = new UsersList();
        productsList = new ProductsList();

        user1 = new User("You");
        usersList.addUserToList(user1);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        bill = new Bill(usersList, productsList);

    }

    //EFFECT uses the USERS input to perform appropriate functions
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
        } else if (input.equals("s")) {
            saveBill();
        } else if (input.equals("l")) {
            loadBill();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    //EFFECT displays the main menu
    private void displayMenu() {
        System.out.println("\nHI!! what would you like to do?\n please select:");
        System.out.println("\ta -> add new user");
        System.out.println("\tb -> Show all users");
        System.out.println("\tc -> Remove an existing user");
        System.out.println("\td -> add an item");
        System.out.println("\te -> view all items");
        System.out.println("\tf -> Remove an item");
        System.out.println("\tg -> Show final balance owed by all users");
        System.out.println("\ts -> to save the current split account");
        System.out.println("\tl -> to load the old split account");
        System.out.println("\tq -> to quit");

    }

    //MODIFIES this
    //EFFECT adds users to usersList
    private void addUser() {
        //adding username
        System.out.println("Please enter the name of user:");
        String input = in.next();
        User u = new User(input);
        usersList.addUserToList(u);

    }

    //EFFECT shows all users and balance owed by them
    private void showUsers() {
        System.out.println(usersList.getAllUsers());
    }

    //MODIFIES this
    //EFFECT removes users from usersList
    private void removeUser() {
        System.out.println("Which user do you want to remove? \n enter their serial number");
        showUsers();
        int input = in.nextInt();
        usersList.removeUserFromList(input - 1);
    }

    //MODIFIES this
    //EFFECT adds products to productsList after taking in details about the product from the user
    private void addItem() {
        Product p = new Product();

        System.out.println("Enter the product name");
        String itemName = in.next();
        p.setName(itemName);

        System.out.println("Enter the cost ");
        double itemCost = in.nextDouble();
        p.setCost(itemCost);

        productsList.addProductToList(p);
        
        System.out.println("If item is split between specific people only then press 1 "
                + "\n else press anything else to return to menu");
        int input = in.nextInt();

        if (input == 1) {
            splitBetween(p);
        } else {
            splitToAll(p);
        }


    }

    //MODIFIES user.balance
    //EFFECT splits the balance of an item shared by everyone, between every person
    private void splitToAll(Product p) {
        double splitBalance = (p.getCost() / usersList.getSize());
        for (int i = 0; i < usersList.getSize(); i++) {
            User u = usersList.getUserFromList(i);
            u.addBalance(splitBalance);
        }

    }

    //EFFECT splits the cost of an item between specific users
    private void splitBetween(Product p) {
        System.out.println("Enter the number of people using this item");
        int num = in.nextInt();
        split(num, p);


    }

    /*MODIFIES user.balance && product.users
     *EFFECT :
     *  makes a Integer List to store the serial numbers of Users entered by the USER
     *  accordingly adds those Users(using those serial numbers) to a list
     *  sends the list to Product.setProdUsers to set the Users using that product.
     *
     */
    private void split(int num, Product p) {
        List<Integer> x = new ArrayList<Integer>();
        UsersList list = new UsersList();

        for (int i = 0; i < num; i++) {
            System.out.println("Enter the sr. number of the person using this item");
            showUsers();
            int y = in.nextInt();
            x.add(y - 1);
        }

        double splitCost = (p.getCost() / num);


        for (int i = 0; i < x.size(); i++) {
            User u = usersList.getUserFromList(x.get(i));
            u.addBalance(splitCost);
            list.addUserToList(u);
        }
        p.setListOfUsers(list);

    }

    //EFFECT shows all the products and their details.
    private void showItems() {
        System.out.println(productsList.getAllProducts());
    }

    //MODIFIES this
    //EFFECT removes products for productsList and calls deduct()
    private void removeItem() {
        System.out.println("choose product to remove");
        showItems();
        int i = in.nextInt();
        Product p = productsList.getProduct(i - 1);
        productsList.removeProductFromList(p);
        deduct(p);
        System.out.println("Removed product:" + p.getName() + "\nShared by: " + p.getProdUsers());

    }

    //EFFECT checks if product is being shared by some users or all
    private void deduct(Product p) {

        if (p.getListOfUsers().getSize() == 0) {
            deductFromAll(p);
        } else {
            deductFromSome(p);
        }
    }

    //MODIFIES User.balance
    //EFFECT deducts owed balance of the product only from Users sharing the specified product
    private void deductFromSome(Product p) {
        UsersList prodUsers = p.getListOfUsers();
        double splitBalance = (p.getCost() / prodUsers.getSize());

        System.out.println("Deducted owed balance of: ");
        for (int i = 0; i < prodUsers.getSize(); i++) {
            prodUsers.getUserFromList(i).deductBalance(splitBalance);
            System.out.println(i + ") " + prodUsers.getUserName(i));
        }
    }

    //MODIFIES user.balance
    //EFFECT deducts owed balance of the product from all users
    private void deductFromAll(Product p) {
        double splitBalance = (p.getCost() / usersList.getSize());
        System.out.println("Deducting owed balance from ALL");
        for (int i = 0; i < usersList.getSize(); i++) {
            usersList.getUserFromList(i).deductBalance(splitBalance);
        }
        System.out.println("Done deducting from ALL.");

    }


    //EFFECT shows total balance and balance owed by all users
    private void finalBalance() {
        System.out.println("Total cost = " + productsList.getTotalBalance());
        System.out.println(bill.toString());
    }

    // EFFECTS: saves the workroom to file
    private void saveBill() {
        try {
            System.out.println("Name that you want to give your Bill:");
            String inp1 = in.next();
            bill.setName(inp1);

            jsonWriter.open();
            jsonWriter.write(bill);
            jsonWriter.close();
            System.out.println("Saved " + bill.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadBill() {
        try {
            bill = jsonReader.readBill();
            System.out.println("Loaded " + bill.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
