package ui;

import model.Bill;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserPage extends JFrame {

    private Bill bill;
    private JPanel addUserPanel;
    private JLabel titleLabel;
    private JLabel nameLabel;

    private JTextField userNameTextField;

    private JButton backButton;
    private JButton addUserButton;

    public AddUserPage(Bill bill) {
        this.bill = bill;
        add(addUserPanel);
        setTitle("Add-User Page");
        setSize(400,500);

        addUserButtonPressed(bill);
        backButtonPressed();

    }

    private void addUserButtonPressed(Bill bill) {
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = userNameTextField.getText();

                if (s.equals("")) {
                    JOptionPane.showMessageDialog(addUserPanel, "Please enter a name");
                } else {
                    String userName = userNameTextField.getText();
                    User user = new User(userName);
                    bill.getUsersList().addUserToList(user);

                    JOptionPane.showMessageDialog(addUserPanel, "Added User");
                    userNameTextField.setText("");
                }
            }
        });
    }

    private void backButtonPressed() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersRelated usersRelated = new UsersRelated(bill);
                usersRelated.setVisible(true);
                dispose();
            }
        });
    }
}
