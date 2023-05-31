import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmDatabase {
    private Connection connection;

    public FilmDatabase() {
        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://sql7.freesqldatabase.com:3306/sql7620473", "sql7620473", "KLMnvqXAWF");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFilm(Film film) {
        try {
            String query = "INSERT INTO films (title, director, genre, releaseYear, runTime) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, film.getTitle());
            statement.setString(2, film.getDirector());
            statement.setString(3, film.getGenre());
            statement.setInt(4, film.getReleaseYear());
            statement.setInt(5,film.getRunTime());
            statement.executeUpdate();
            statement.close();
            System.out.println("Film wurde erfolgreich Hinzugefügt!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Film searchFilmByTitle(String title) {
        String query = "SELECT * FROM films WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Film film = new Film(
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getString("genre"),
                        resultSet.getInt("releaseYear"),
                        resultSet.getInt("runTime")
                );
                return film;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Film searchFilmByDirector(String director) {
        String query = "SELECT * FROM films WHERE director = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, director);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Film film = new Film(
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getString("genre"),
                        resultSet.getInt("releaseYear"),
                        resultSet.getInt("runTime")
                );
                return film;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public Film searchFilmByGenre(String genre) {
        String query = "SELECT * FROM films WHERE genre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genre);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Film film = new Film(
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getString("genre"),
                        resultSet.getInt("releaseYear"),
                        resultSet.getInt("runTime")
                );
                return film;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Film searchFilmByYear(int releaseYear) {
        String query = "SELECT * FROM films WHERE releaseYear = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, releaseYear);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Film film = new Film(
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getString("genre"),
                        resultSet.getInt("releaseYear"),
                        resultSet.getInt("runTime")
                );
                return film;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void displayFilmDetails(Film film) {
        if (film != null) {
            System.out.println("Title: " + film.getTitle());
            System.out.println("Regisseur: " + film.getDirector());
            System.out.println("Genre: " + film.getGenre());
            System.out.println("Erscheinungsjahr: " + film.getReleaseYear());

            int runtimeInMinutes = film.getRunTime();
            int hours = runtimeInMinutes / 60;
            int minutes = runtimeInMinutes % 60;

            System.out.println("Laufzeit: " + hours + " Stunden " + minutes + " Minuten");
        } else {
            System.out.println("Film not found!");
        }
    }

}