package ui;

import model.Bill;
import model.User;
import model.UsersList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductPage extends JFrame {
    private JPanel addProductPanel;
    private JList list1;

    //buttons
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton backButton;

    //textFields
    private JTextField textField1;
    private JTextField textField2;

    public AddProductPage(Bill bill) {
        add(addProductPanel);
        setTitle("Add-product page");
        setSize(400,500);

        addProdUsers(bill);
        addProductButtonPressed(bill);
        removeProductButtonPressed(bill);
        backButtonPressed(bill);



    }

    private void addProdUsers(Bill bill) {
        UsersList usersList = bill.getUsersList();
        for (int i = 0; i < usersList.getSize(); i++) {
            list1.add(null,usersList.getUserFromList(i).getName());
            System.out.println(usersList.getUserFromList(i).getName());
        }
    }

    private void addProductButtonPressed(Bill bill) {
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void removeProductButtonPressed(Bill bill) {
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void backButtonPressed(Bill bill) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductRelated productRelatedPage = new ProductRelated(bill);
                productRelatedPage.setVisible(true);
                dispose();
            }
        });
    }

}
