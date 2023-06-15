package GUI;

import Backend.Film;
import Backend.FilmDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SearchFilmGUI extends JFrame {

    private final FilmDatabase filmDatabase;
    private final JTextField searchField;
    private final JTextArea outputArea;
    private boolean adminAccessGranted;

    public SearchFilmGUI() {
        filmDatabase = new FilmDatabase();
        adminAccessGranted = false;

        setTitle("Filme suchen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 110, 30, 110));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1, 1, 3));

        searchField = new JTextField(40);

        JButton searchButton = new JButton("Film suchen");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilmButtonClicked();
            }
        });

        inputPanel.add(searchField);
        inputPanel.add(new JLabel());
        inputPanel.add(searchButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputArea = new JTextArea(8, 20);
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);

        outputPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        adminPanel.add(Box.createVerticalStrut(8)); // Add vertical spacing
        adminPanel.add(outputPanel);

        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAdminPanel();
            }
        });

        JPanel adminButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adminButtonPanel.add(adminButton);

        adminPanel.add(adminButtonPanel);

        mainPanel.add(adminPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void searchFilmButtonClicked() {
        String searchTerm = searchField.getText();

        Film film;

        film = filmDatabase.searchFilmByTitle(searchTerm);
        if (film != null) {
            outputArea.setText("Film gefunden (nach Titel):\n");
            displayFilmDetails(film);
            return;
        }

        film = filmDatabase.searchFilmByDirector(searchTerm);
        if (film != null) {
            outputArea.setText("Film gefunden (nach Regisseur):\n");
            displayFilmDetails(film);
            return;
        }

        film = filmDatabase.searchFilmByGenre(searchTerm);
        if (film != null) {
            outputArea.setText("Film gefunden (nach Genre):\n");
            displayFilmDetails(film);
            return;
        }

        try {
            int releaseYear = Integer.parseInt(searchTerm);
            film = filmDatabase.searchFilmByYear(releaseYear);
            if (film != null) {
                outputArea.setText("Film gefunden (nach Erscheinungsjahr):\n");
                displayFilmDetails(film);
                return;
            }
        } catch (NumberFormatException e) {
            // Ignore if the search term is not a valid number
        }

        outputArea.setText("Kein Film gefunden für den Suchbegriff \"" + searchTerm + "\".");
    }

    private void displayFilmDetails(Film film) {
        outputArea.append("Titel: " + film.getTitle() + "\n");
        outputArea.append("Regisseur: " + film.getDirector() + "\n");
        outputArea.append("Genre: " + film.getGenre() + "\n");
        outputArea.append("Erscheinungsjahr: " + film.getReleaseYear() + "\n");

        int runtimeInMinutes = film.getRunTime();
        int hours = runtimeInMinutes / 60;
        int minutes = runtimeInMinutes % 60;

        outputArea.append("Laufzeit: " + hours + " Stunden " + minutes + " Minuten\n");
    }

    private void openAdminPanel() {
        if (adminAccessGranted) {
            JOptionPane.showMessageDialog(this, "Admin panel already open!");
            return;
        }

        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridLayout(3, 2, 10, 10));
        adminPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] password = passwordField.getPassword();
                if (checkPassword(password)) {
                    adminAccessGranted = true;
                    //JOptionPane.showMessageDialog(SearchFilmGUI.this, "Admin access granted!");

                    // Open AddFilmGUI when admin access is granted
                    AddFilmGUI addFilmGUI = new AddFilmGUI();
                    addFilmGUI.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(SearchFilmGUI.this, "Incorrect password. Try again.");
                }
                passwordField.setText(""); // Clear the password field
            }
        });

        adminPanel.add(passwordLabel);
        adminPanel.add(passwordField);
        adminPanel.add(new JLabel());
        adminPanel.add(loginButton);

        JOptionPane.showOptionDialog(this, adminPanel, "Admin Panel",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    private boolean checkPassword(char[] password) {
        char[] correctPassword = "admin".toCharArray(); // Replace with your desired password
        return Arrays.equals(password, correctPassword);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SearchFilmGUI searchFilmGUI = new SearchFilmGUI();
                searchFilmGUI.setVisible(true);
            }
        });
    }
}
