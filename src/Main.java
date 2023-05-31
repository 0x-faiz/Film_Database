import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FilmDatabase filmDatabase = new FilmDatabase();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("Film-Datenbank Anwendung");
            System.out.println("1. Film hinzufügen");
            System.out.println("2. Filme nach Titel suchen");
            System.out.println("3. Filme nach Regisseur suchen");
            //System.out.println("4. Filme nach Erscheinungsjahr Suchen");
            System.out.println("4. Beenden");
            System.out.print("Geben Sie Ihre Auswahl ein: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Zeilenumbruch einlesen

            switch (choice) {
                case 1:
                    System.out.print("Geben Sie den Filmtitel ein: ");
                    String title = scanner.nextLine();
                    System.out.print("Geben Sie den Filmregisseur ein: ");
                    String director = scanner.nextLine();
                    System.out.print("Geben Sie das Filmgenre ein: ");
                    String genre = scanner.nextLine();
                    System.out.print("Geben Sie das Erscheinungsjahr des Films ein: ");
                    int releaseYear = scanner.nextInt();
                    System.out.println("Geben sie die Laufzeit an in Minuten: ");
                    int runTime = scanner.nextInt();
                    scanner.nextLine(); // Zeilenumbruch einlesen

                    Film film = new Film(title, director, genre, releaseYear, runTime );
                    filmDatabase.addFilm(film);
                    System.out.println("Film erfolgreich hinzugefügt!");
                    break;

                case 2:
                    System.out.print("Geben Sie den Titel zur Suche ein: ");
                    String searchTitle = scanner.nextLine();
                    Film filmByTitle = filmDatabase.searchFilmByTitle(searchTitle);
                    if (filmByTitle != null) {
                        System.out.println("Suchergebnis:");
                        filmDatabase.displayFilmDetails(filmByTitle);
                    } else {
                        System.out.println("Film nicht gefunden.");
                    }
                    break;

                case 3:
                    System.out.print("Geben Sie den Regisseur zur Suche ein: ");
                    String searchDirector = scanner.nextLine();
                    Film filmByDirector = filmDatabase.searchFilmByDirector(searchDirector);
                    if (filmByDirector != null) {
                        System.out.println("Suchergebnis:");
                        filmDatabase.displayFilmDetails(filmByDirector);
                    } else {
                        System.out.println("Film nicht gefunden.");
                    }
                    break;

                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
            }

            System.out.println();
        }

        System.out.println("Vielen Dank für die Nutzung der Film-Datenbank Anwendung!");
    }
}