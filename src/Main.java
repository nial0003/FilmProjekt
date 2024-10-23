import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MovieCollection listOfMovies = new MovieCollection();
        Scanner sc = new Scanner(System.in);

        System.out.println("!---------- Welcome to your movie collection!----------!");

        while (true) {
            System.out.println("""
                    1. Create movie
                    2. Exit
                    """);
            String input = sc.next();
            switch (input) {
                case "1", "create", "create movie" -> {
                    System.out.println("Please input: Title, director, year created, is in color, length in minutes and genre\n");
                    System.out.print("Title: ");
                    String movieTitel = sc.next();
                    System.out.print("Director name: ");
                    String movieDirector = sc.next();
                    System.out.print("Year created: ");
                    int yearCreated = sc.nextInt();
                    System.out.print("Is the movie in color (yes/no): ");
                    boolean inColor = false;
                    boolean validInput = false;
                    while (!validInput) {
                        String isInColor = sc.next();
                        if (isInColor.equalsIgnoreCase("no")) {
                            inColor = false;
                            validInput = true;
                        } else if (isInColor.equalsIgnoreCase("yes")) {
                            inColor = true;
                            validInput = true;
                        } else {
                            System.out.println("Invalid input, please write 'yes' or 'no'");
                        }
                    }
                    System.out.print("Length in minutes: ");
                    int lengthInMinutes = sc.nextInt();
                    System.out.print("Genre: ");
                    String genre = sc.next();

                    listOfMovies.addMovie(movieTitel, movieDirector, yearCreated, inColor, lengthInMinutes, genre);
                }
                case "2", "exit" -> {
                    System.out.println("Exiting your movie collection.");
                    return;
                }
            }
        }
    }
}
