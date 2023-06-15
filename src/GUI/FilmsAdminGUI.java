package GUI;

import Backend.Film;
import Backend.FilmDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilmsAdminGUI extends JFrame {

    private final FilmDatabase filmDatabase;
    private final JTextField titleField;
    private final JTextField directorField;
    private final JTextField genreField;
    private final JTextField releaseYearField;
    private final JTextField runtimeField;
    private final JTextField deleteFilmField;

    public FilmsAdminGUI() {
        filmDatabase = new FilmDatabase();

        setTitle("Filme Hinzufügen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(50, 50));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel titleLabel = new JLabel("Filmtitel:");
        titleField = new JTextField(30);

        JLabel directorLabel = new JLabel("Filmregisseur:");
        directorField = new JTextField(30);

        JLabel genreLabel = new JLabel("Filmgenre:");
        genreField = new JTextField(30);

        JLabel releaseYearLabel = new JLabel("Erscheinungsjahr:");
        releaseYearField = new JTextField(30);

        JLabel runtimeLabel = new JLabel("Laufzeit (Minuten):");
        runtimeField = new JTextField(30);

        JButton addButton = new JButton("Film hinzufügen");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFilmButtonClicked();
            }
        });

        JLabel deleteFilmLabel = new JLabel("Filmtitel zum Löschen:");
        deleteFilmField = new JTextField(30);

        JButton deleteButton = new JButton("Film löschen");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFilmButtonClicked();
            }
        });

        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(directorLabel);
        inputPanel.add(directorField);
        inputPanel.add(genreLabel);
        inputPanel.add(genreField);
        inputPanel.add(releaseYearLabel);
        inputPanel.add(releaseYearField);
        inputPanel.add(runtimeLabel);
        inputPanel.add(runtimeField);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new GridLayout(2, 2, 10, 10));

        deletePanel.add(deleteFilmLabel);
        deletePanel.add(deleteFilmField);
        deletePanel.add(new JLabel());
        deletePanel.add(deleteButton);

        mainPanel.add(deletePanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void addFilmButtonClicked() {
        String title = titleField.getText();
        String director = directorField.getText();
        String genre = genreField.getText();
        int releaseYear = Integer.parseInt(releaseYearField.getText());
        int runtime = Integer.parseInt(runtimeField.getText());

        Film film = new Film(title, director, genre, releaseYear, runtime);
        filmDatabase.addFilm(film);

        // Clear input fields
        titleField.setText("");
        directorField.setText("");
        genreField.setText("");
        releaseYearField.setText("");
        runtimeField.setText("");

        JOptionPane.showMessageDialog(this, "Film wurde erfolgreich hinzugefügt!", "Erfolgreich", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteFilmButtonClicked() {
        String filmName = deleteFilmField.getText();
        boolean success = filmDatabase.deleteFilmByName(filmName);
        if (success) {
            JOptionPane.showMessageDialog(this, "Film erfolgreich gelöscht!");
        } else {
            JOptionPane.showMessageDialog(this, "Film konnte nicht gelöscht werden.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FilmsAdminGUI addFilmGUI = new FilmsAdminGUI();
                addFilmGUI.setVisible(true);
            }
        });
    }
}