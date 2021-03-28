package ui;

import model.Bill;

import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainMenuPage extends JFrame {
    private static final String JSON_STORE = "./data/SplittingApp.json";
    JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

    private JPanel rootPanel1;

    //Buttons
    private JButton showUsersButton;
    private JButton viewBillButton;
    private JButton saveBillButton;
    private JButton backButton;

    public MainMenuPage(Bill bill) {
        add(rootPanel1);
        setTitle("Main Menu");
        setSize(400,500);

        showAllUsersButtonPressed(bill);
        viewBillButtonPressed(bill);
        saveBillButtonPressed(bill);
        backButtonPressed();
    }

    private void showAllUsersButtonPressed(Bill bill) {
        showUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersRelated usersRelated = new UsersRelated(bill);
                usersRelated.setVisible(true);
                dispose();
            }
        });
    }

    private void viewBillButtonPressed(Bill bill) {
        viewBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillPage billPage = new BillPage(bill);
                billPage.setVisible(true);
                dispose();
            }
        });
    }

    private void saveBillButtonPressed(Bill bill) {
        saveBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bill.setName("Split Bill");

                try {
                    jsonWriter.open();
                    jsonWriter.write(bill);
                    jsonWriter.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showMessageDialog(rootPanel1,"Error: File not Saved!");
                }

            }
        });
    }

    private void backButtonPressed() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(rootPanel1, "Changes won't be saved, Continue?");
                if (i == 0) {
                    WelcomePage welcomePage = null;
                    try {
                        welcomePage = new WelcomePage();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    welcomePage.setVisible(true);
                    dispose();
                }
            }
        });
    }








}
