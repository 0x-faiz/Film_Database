package GUI;

import Back_End.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFilmGUI extends JFrame {

    private final FilmDatabase filmDatabase;
    private final JTextField titleField;
    private final JTextField directorField;
    private final JTextField genreField;
    private final JTextField releaseYearField;
    private final JTextField runtimeField;

    public AddFilmGUI() {
        filmDatabase = new FilmDatabase();

        setTitle("Filme Hinzufügen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel titleLabel = new JLabel("Filmtitel:");
        titleField = new JTextField(20);

        JLabel directorLabel = new JLabel("Filmregisseur:");
        directorField = new JTextField(20);

        JLabel genreLabel = new JLabel("Filmgenre:");
        genreField = new JTextField(20);

        JLabel releaseYearLabel = new JLabel("Erscheinungsjahr:");
        releaseYearField = new JTextField(20);

        JLabel runtimeLabel = new JLabel("Laufzeit (Minuten):");
        runtimeField = new JTextField(20);

        JButton addButton = new JButton("Back_End.Film hinzufügen");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFilmButtonClicked();
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

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(scrollPane, BorderLayout.SOUTH);

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

        JOptionPane.showMessageDialog(this, "Back_End.Film wurde erfolgreich hinzugefügt!", "Erfolgreich", JOptionPane.INFORMATION_MESSAGE);
    }
}
