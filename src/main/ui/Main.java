package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        try {
            new SplittingApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file");
        }
    }
}
