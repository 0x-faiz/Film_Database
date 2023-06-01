import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AddFilmGUI addFilmGUI = new AddFilmGUI();
                addFilmGUI.setVisible(true);
            }
        });
    }
}
