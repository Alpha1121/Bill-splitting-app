package ui;

import model.*;
import persistence.JsonWriter;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WelcomePage extends JFrame {
    private static final String JSON_STORE = "./data/SplittingApp.json";
    JsonReader j1 = new JsonReader(JSON_STORE);
    Bill bill = j1.readBill();
    private JPanel rootPanel;
    private JButton loadBillButton;
    private JButton createNewBillButton;


    public WelcomePage() throws IOException {
        add(rootPanel);
        setTitle("Bill Splitting App");
        setSize(400,500);

        loadBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = JOptionPane.showConfirmDialog(rootPanel, "Load old bill?");
                if (i == 0) {
                    MainMenuPage mainMenuPage = new MainMenuPage(bill);
                    mainMenuPage.setVisible(true);
                    dispose();
                }
            }
        });

        createNewBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(rootPanel, "Create New bill?");

                //create object
                bill = new Bill("New Bill");
                MainMenuPage mainMenuPage = new MainMenuPage(bill);
                mainMenuPage.setVisible(true);
                dispose();

            }
        });
    }
}
