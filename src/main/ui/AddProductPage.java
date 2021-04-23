package ui;

import model.Bill;
import model.Product;
import model.User;
import model.UsersList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddProductPage extends JFrame {
    private JPanel addProductPanel;

    //List related variables
    private JList list1;
    private DefaultListModel<String> listModel1 = new DefaultListModel<>();
    private UsersList usersList1 = new UsersList();
    private int selectedIndex1;
    private String selectedName1;

    private JList list2;
    private DefaultListModel<String> listModel2 = new DefaultListModel<>();
    private UsersList usersList2 = new UsersList();
    private int selectedIndex2;
    private String selectedName2;

    //buttons
    private JButton addProductButton;
    private JButton backButton;
    private JButton addUserButton;
    private JButton removeUserButton;

    //textFields
    private JTextField prodName;
    private JTextField prodCost;

    public AddProductPage(Bill bill) {
        add(addProductPanel);
        setTitle("Add-product page");
        setSize(700,500);

        getUsers(bill);

        userSelectedInList1();
        showUsersList1();

        userSelectedInList2();
        showUsersList2();
        
        addUserButtonPressed();
        removeUserButtonPressed();
        
        addProductButtonPressed(bill);

        backButtonPressed(bill);

    }

    //puts all users of bill.usersList into List1.
    private void getUsers(Bill bill) {
        UsersList usersList = bill.getUsersList();
        for (int i = 0; i < usersList.getSize(); i++) {
            User u = usersList.getUserFromList(i);
            usersList1.addUserToList(u);
        }
    }

    //puts all the users in List1 into the listModel.
    private void showUsersList1() {
        for (int i = 0; i < usersList1.getSize(); i++) {
            User u = usersList1.getUserFromList(i);
            listModel1.addElement(u.getName());
        }
        list1.setModel(listModel1);
    }


    private void showUsersList2() {
        list2.setModel(listModel2);
    }

    //EFFECTS: Checks to see if the app user, clicked on a User in List1.
    private void userSelectedInList1() {
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = list1.getSelectedIndex();
                if (i >= 0) {
                    selectedIndex1 = i;
                    selectedName1 = listModel1.get(selectedIndex1);
                }
            }
        });
    }

    //EFFECTS: adds the selected user in List1, to List2. While also at the same time deleting the user in List1.
    private void addUserButtonPressed() {
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < usersList1.getSize(); i++) {
                    User u = usersList1.getUserFromList(i);

                    //EFFECTS: Checks to see if the selected name is equal to the name of the current iterated
                    //         user from the for loop.
                    if (selectedName1.contains(u.getName())) {
                        usersList2.addUserToList(u);
                        usersList1.removeUserFromList(i);
                    }
                }

                //EFFECTS: Checks to see if a user has been selected in the listModel1 users. If not then returns a
                //         message.
                if (selectedIndex1 <= listModel1.getSize() && selectedIndex1 >= 0) {
                    listModel1.remove(selectedIndex1);
                } else {
                    JOptionPane.showMessageDialog(addProductPanel, "Please select User");
                }
                listModel2.addElement(selectedName1);
            }
        });
    }

    //EFFECTS: Checks to see if the app user, clicked on a User in List1.
    private void userSelectedInList2() {
        list2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = list2.getSelectedIndex();
                if (i >= 0) {
                    selectedIndex2 = i;
                    selectedName2 = listModel2.get(selectedIndex2);
                }
            }
        });
    }



    //EFFECTS: adds the selected user in List2, to List1. While also at the same time deleting the user in List2.

    private void removeUserButtonPressed() {
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < usersList2.getSize(); i++) {
                    User u = usersList2.getUserFromList(i);

                    //EFFECTS: Checks to see if the selected name is equal to the name of the current iterated
                    //         user from the for loop.
                    if (selectedName2.contains(u.getName())) {
                        usersList1.addUserToList(u);
                        usersList2.removeUserFromList(i);
                    }
                }

                //EFFECTS: Checks to see if a user has been selected in the listModel2 users. If not then returns a
                //         message.
                if (selectedIndex2 >= 0) {
                    listModel2.remove(selectedIndex2);
                } else {
                    JOptionPane.showMessageDialog(addProductPanel, "Please select user");
                }
                listModel1.addElement(selectedName2);
            }
        });
    }



    private void addProductButtonPressed(Bill bill) {
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks if the text in the TextField is empty, if it is, shows an error
                if (prodName.getText().equals("") || prodCost.getText().equals("")) {
                    JOptionPane.showMessageDialog(addProductPanel,
                            "Please check the product name and cost again");
                } else {

                    /*
                     Checks to see if the entered value in the prodCost textField is an Integer or not, if not then
                     shows an error.
                     */
                    if (isInteger(prodCost.getText())) {
                        addProduct(bill);
                    } else {
                        JOptionPane.showMessageDialog(addProductPanel, "Please enter a valid amount");
                    }
                }
            }
        });
    }


    //EFFECTS: Called when the addProduct Button is pushed, adds the product to bill.productsList,and also
    //         in bill.usersList.productsList .
    private void addProduct(Bill bill) {
        int cost = Integer.parseInt(prodCost.getText());
        Product p = new Product(prodName.getText(), cost, usersList2);
        bill.getProductsList().addProductToList(p);
        bill.getUsersList().getProductsList().addProductToList(p);


        JOptionPane.showMessageDialog(addProductPanel, "Product added to bill: " + p.getName());
        AddProductPage addProductPage = new AddProductPage(bill);
        addProductPage.setVisible(true);
        dispose();
    }

    //EFFECTS: Checks if character is a digit from 0-9 then returns true, else false
    private boolean isInteger(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0'
                    && str.charAt(i) <= '9') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    //Effects: Takes the user back to the MainMenuPage.
    private void backButtonPressed(Bill bill) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillPage billPage = new BillPage(bill);
                billPage.setVisible(true);
                dispose();
            }
        });
    }

}
