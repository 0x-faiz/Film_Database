package Backend;

import GUI.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SearchFilmGUI SearchFilmGUI = new SearchFilmGUI();
                SearchFilmGUI.setVisible(true);
                System.out.println("Program startet successfully");
            }
        });
    }
}
