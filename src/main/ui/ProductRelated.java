package ui;

import model.Bill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductRelated extends JFrame {
    private JTable tblProds;
    private DefaultTableModel defaultTable;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton backButton;
    private JPanel productsPanel;
    private Bill bill;

    public ProductRelated(Bill bill) {
        add(productsPanel);
        setTitle("Products Menu");
        setSize(400, 500);
        this.bill = bill;

//        showProducts(bill);
        addProductButtonPressed(bill);
//        removeProductButtonPressed(bill);
        backButtonPressed();


    }

    private void showProducts(Bill bill) {
        //show list of products on the bill

    }

    private void addProductButtonPressed(Bill bill) {
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductPage addProductPage = new AddProductPage(bill);
                addProductPage.setVisible(true);
                dispose();
            }
        });
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
