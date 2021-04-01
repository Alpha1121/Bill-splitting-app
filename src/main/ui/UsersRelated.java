package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UsersRelated extends JFrame {
    // Variables declaration
    private JPanel usersPanel;
    private javax.swing.JLabel label1;
    private Bill bill;

    //List related declarations
    private JList<String> list1;
    private DefaultListModel<String> listModel = new DefaultListModel<String>();
    private int selectedIndex;
    private String selectedName;

    //Buttons
    private JButton addUserButton;
    private JButton removeUserButton;
    private JButton backButton;

    public UsersRelated(Bill bill) {
        getContentPane().add(usersPanel);


        setTitle("User Page");
        setSize(400,500);
        this.bill = bill;

        showUsers(bill);

        userSelectedInList();

        addUserButtonPressed(bill);
        removeUserButtonPressed(bill);
        backButtonPressed();
    }


    private void showUsers(Bill bill) {

        UsersList usersList = bill.getUsersList();

        for (int i = 0; i < usersList.getSize(); i++) {
            User user = usersList.getUserFromList(i);

            listModel.addElement(user.getName() + "         " + user.getBalance());
        }
        list1.setModel(listModel);
    }

    private void addUserButtonPressed(Bill bill) {
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserPage addUserPage = new AddUserPage(bill);
                addUserPage.setVisible(true);
                dispose();
            }
        });
    }

    private void userSelectedInList() {
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = list1.getSelectedIndex();

                if (index >= 0) {
                    selectedIndex = index;
                    selectedName = listModel.get(index);
                } else {
                    JOptionPane.showMessageDialog(usersPanel, "Please add new User");
                }


            }
        });
    }

    //MODIFIES: bill.usersList
    //EFFECTS: removes user from bill.usersList.
    private void removeUserButtonPressed(Bill bill) {
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //iterates through bill.UsersList to find the selected User
                for (int i = 0; i < bill.getUsersList().getSize(); i++) {
                    User u = bill.getUsersList().getUserFromList(i);

                    //Checks if th e iterator is rightly on the  selected name
                    if (selectedName.contains(u.getName())) {
                        bill.removeUserFromBill(u);
                        removeUserFromProdUsersList(bill, u);
                        JOptionPane.showMessageDialog(usersPanel, "User removed: " + u.getName());
                    }
                }

                UsersRelated usersRelated = new UsersRelated(bill);
                usersRelated.setVisible(true);
                dispose();

            }
        });

    }

    private void removeUserFromProdUsersList(Bill bill, User u) {
        ProductsList productsList = bill.getProductsList();

        //iterates through bill.productsList to find the product which is used by the
        for (int i1 = 0; i1 < productsList.getSize(); i1++) {
            Product p = productsList.getProduct(i1);
            System.out.println("Checking product:" + p.getName());
            UsersList prodUsersList = p.getListOfUsers();

            for (int i2 = 0; i2 < prodUsersList.getSize(); i2++) {

                User prodUser = prodUsersList.getUserFromList(i2);
                System.out.println("Checking User in list:" + prodUser.getName());

                if (selectedName.contains(prodUser.getName())) {
                    prodUsersList.removeUserFromList(prodUser);
                    Product newProd = new Product(p.getName(),p.getCost(), prodUsersList);
                    newProd.split(bill);

                    productsList.removeProductFromList(p, bill);
                    productsList.addProductToList(newProd);
                }
            }
        }
    }

    private void backButtonPressed() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuPage mainMenuPage = new MainMenuPage(bill);
                mainMenuPage.setVisible(true);
                dispose();
            }
        });
    }

}
