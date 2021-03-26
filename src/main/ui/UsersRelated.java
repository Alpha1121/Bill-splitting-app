package ui;

import model.Bill;
import model.User;
import model.UsersList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UsersRelated extends JFrame {
    // Variables declaration

    private JPanel usersPanel;
    private javax.swing.JLabel label1;
    private javax.swing.JTable tblUsers;
    private DefaultTableModel defaultTable;
    private JButton backButton;
    private JButton removeUserButton;
    private JButton addUserButton;


    public UsersRelated(Bill bill) {
        add(usersPanel);
        setTitle("User Menu");
        setSize(400,500);

        showUsers(bill);
//        addUserButtonPressed(bill);
//        removeUserButtonPressed(bill);
//        backButtonPressed();
    }

    private void initializeTable() {
        tblUsers = new JTable();
        defaultTable = (DefaultTableModel) tblUsers.getModel();

    }

    @SuppressWarnings("checkstyle:ArrayTypeStyle")
    private void showUsers(Bill bill) {

        UsersList usersList = bill.getUsersList();
        for (int i = 0; i < usersList.getSize(); i++) {
            User user = usersList.getUserFromList(i);
            System.out.println(user.toString());
            String[] o = {user.getName(), String.valueOf(user.getBalance())};
            defaultTable.addRow(o);

            int[] integers = {1,2};
            defaultTable.addRow(new int[][]{integers});
        }

    }

    private void addUserButtonPressed(Bill bill) {
    }

    private void removeUserButtonPressed(Bill bill) {
    }

    private void backButtonPressed() {
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        initializeTable();
    }
}
