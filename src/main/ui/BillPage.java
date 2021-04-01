package ui;

import model.Bill;
import model.Product;
import model.ProductsList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BillPage extends JFrame {
    private Bill bill;
    private JPanel billPanel;

    //List related variable declaration
    private JList<String> list1;
    private DefaultListModel<String> listModel = new DefaultListModel<String>();
    private int selectedIndex;
    private String selectedProductName;

    //Labels
    private JLabel prodName;
    private JLabel balance;

    //buttons
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton backButton;

    public BillPage(Bill bill) {
        add(billPanel);
        setTitle("Split Bill");
        setSize(700, 600);
        this.bill = bill;

        showProducts(bill);
        addProductButtonPressed(bill);
        productSelectedInList();
        removeProductButtonPressed(bill);
        backButtonPressed();
        balance.setText("Total Balance : $" + bill.getProductsList().getTotalBalance());

    }


    //EFFECTS: shows list of products
    private void showProducts(Bill bill) {
        ProductsList productsList = bill.getProductsList();

        for (int i = 0; i < productsList.getSize(); i++) {
            Product p = productsList.getProduct(i);
//            listModel.addElement(p.getName() + "                 "
//                    + p.getCost() + "                 "
//                    + "{" + p.getProdUsers() + "}");
            listModel.addElement(p.toString());
        }

        list1.setModel(listModel);

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

    private void productSelectedInList() {
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = list1.getSelectedIndex();
                if (i >= 0) {
                    selectedIndex = i;
                    selectedProductName = listModel.get(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(billPanel, "Please add a Product");
                }
            }
        });
    }

    private void removeProductButtonPressed(Bill bill) {
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductsList productsList = bill.getProductsList();

                for (int i = 0; i < productsList.getSize(); i++) {
                    Product p = productsList.getProduct(i);

                    if (selectedProductName.contains(p.getName())) {
                        productsList.removeProductFromList(p, bill);
                        JOptionPane.showMessageDialog(billPanel, "Product removed: " + p.getName());
                        BillPage billPage = new BillPage(bill);
                        billPage.setVisible(true);
                        dispose();
                    }
                }

                listModel.remove(selectedIndex);
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
