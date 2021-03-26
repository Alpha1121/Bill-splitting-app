package ui;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
//            new SplittingApp();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    WelcomePage welcomePage = null;
                    try {
                        welcomePage = new WelcomePage();
                    } catch (IOException exception) {
                        System.out.println(exception.getCause());
                    }
                    welcomePage.setVisible(true);
                }
            });
       // } catch (FileNotFoundException e) {
       //     System.out.println("Unable to find file");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
