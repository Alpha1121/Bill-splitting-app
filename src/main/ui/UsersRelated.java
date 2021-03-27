package ui;

import model.Bill;
import model.User;
import model.UsersList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersRelated extends JFrame {
    // Variables declaration
    private JPanel usersPanel;
    private javax.swing.JLabel label1;
    private Bill bill;

    private javax.swing.JTable tblUsers;
    private DefaultTableModel defaultTable;

    private JButton addUserButton;
    private JButton removeUserButton;
    private JButton backButton;

    public UsersRelated(Bill bill) {
        getContentPane().add(usersPanel);
//        getContentPane().setLayout(new BorderLayout());

//        add(usersPanel);
        setTitle("User Menu");
        setSize(400,500);
        this.bill = bill;

        initializeTable();

        showUsers(bill);
        addUserButtonPressed(bill);
//        removeUserButtonPressed(bill);
        backButtonPressed();


    }

    private void initializeTable() {
//        tblUsers = new JTable();
        defaultTable = (DefaultTableModel) tblUsers.getModel();
        tblUsers.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }

    private void showUsers(Bill bill) {

        UsersList usersList = bill.getUsersList();
        for (int i = 0; i < usersList.getSize(); i++) {
            User user = usersList.getUserFromList(i);
            System.out.println(user.toString());
            String[] o = {user.getName(), String.valueOf(user.getBalance())};
            defaultTable.addRow(o);
            System.out.println("Done " + i);
            System.out.println(defaultTable.getRowCount());

        }

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

    private void removeUserButtonPressed(Bill bill) {
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
