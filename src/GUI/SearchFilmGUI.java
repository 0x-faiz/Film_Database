package GUI;

import Backend.Film;
import Backend.FilmDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class SearchFilmGUI extends JFrame {

    private final FilmDatabase filmDatabase;
    private final JTextField searchField;
    private final JTextArea outputArea;
    private boolean adminAccessGranted;

    public SearchFilmGUI() {
        filmDatabase = new FilmDatabase();
        adminAccessGranted = false;

        setTitle("Film Datenbank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(50, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 150, 30, 150));

        JLabel titleLabel = new JLabel("Film Datenbank");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 3, 3));

        JLabel searchLabel = new JLabel("Suche nach Film");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(searchLabel);

        searchField = new JTextField(40);
        inputPanel.add(searchField);

        inputPanel.add(new JLabel());

        JButton searchButton = new JButton("Film suchen");
        searchButton.setBackground(Color.WHITE);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFilmButtonClicked();
            }
        });

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
        adminPanel.add(Box.createVerticalStrut(8));
        adminPanel.add(outputPanel);

        JButton adminButton = new JButton("Admin");
        adminButton.setBackground(Color.white);
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
            searchField.setText("");
            return;
        }

        film = filmDatabase.searchFilmByDirector(searchTerm);
        if (film != null) {
            outputArea.setText("Film gefunden (nach Regisseur):\n");
            displayFilmDetails(film);
            searchField.setText("");
            return;
        }

        film = filmDatabase.searchFilmByGenre(searchTerm);
        if (film != null) {
            outputArea.setText("Film gefunden (nach Genre):\n");
            displayFilmDetails(film);
            searchField.setText("");
            return;
        }

        try {
            int releaseYear = Integer.parseInt(searchTerm);
            film = filmDatabase.searchFilmByYear(releaseYear);
            if (film != null) {
                outputArea.setText("Film gefunden (nach Erscheinungsjahr):\n");
                displayFilmDetails(film);
                searchField.setText("");
                return;
            }
        } catch (NumberFormatException e) {

        }

        outputArea.setText("Kein Film gefunden für den Suchbegriff \"" + searchTerm + "\".");
        searchField.setText("");
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
            JOptionPane.showMessageDialog(this, "Admin panel ist bereits Offen");
            return;
        }

        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridLayout(3, 2, 10, 10));
        adminPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] password = passwordField.getPassword();
                if (checkPassword(password)) {
                    adminAccessGranted = true;


                    FilmsAdminGUI addFilmGUI = new FilmsAdminGUI();
                    addFilmGUI.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(SearchFilmGUI.this,
                            "Falsches Passwort. Bitte versuchen Sie es erneut.");
                }
                passwordField.setText("");
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
        char[] correctPassword = "admin".toCharArray();
        return Arrays.equals(password, correctPassword);
    }
}
