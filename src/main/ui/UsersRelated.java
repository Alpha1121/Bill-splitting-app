package ui;

import model.Bill;
import model.User;
import model.UsersList;

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
    private int selectedIndex;
    private String selectedName;

    //List related declarations
    private JList<String> list1;
    private DefaultListModel<String> listModel = new DefaultListModel<String>();

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

    private void removeUserButtonPressed(Bill bill) {
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersList usersList = bill.getUsersList();

                for (int i = 0; i < usersList.getSize(); i++) {
                    User u = usersList.getUserFromList(i);

                    if (selectedName.contains(u.getName())) {
                        usersList.removeUserFromList(i);
                        JOptionPane.showMessageDialog(usersPanel, "User removed: " + u.getName());
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
