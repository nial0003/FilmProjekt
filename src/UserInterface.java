import java.util.Scanner;

public class UserInterface {
    Controller cont = new Controller();
    Scanner sc = new Scanner(System.in);

    public void startProgram(){

//        Controller cont = new Controller();
//        Scanner sc = new Scanner(System.in);
        cont.addMovie("Inception", "Christopher Nolan", 2010, true, 148, "Science Fiction");
        cont.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime/Drama");
        cont.addMovie("Pulp Fiction", "Quentin Tarantino", 1994, true, 154, "Crime");
        cont.addMovie("Schindler's List", "Steven Spielberg", 1993, true, 195, "Historical/Drama");
        cont.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Romance/Drama");
        cont.addMovie("Skyfall", "Sam Mendes", 2012, true, 143, "Action");

        System.out.println("!---------- Welcome to your movie collection!----------!");

        while (true) {
            System.out.println("""
                    1. Create movie
                    2. Show movies
                    3. Search movie
                    4. Exit
                    """);
            String input = sc.next();
            switch (input) {
                case "1", "create", "create movie" -> createMovieCase();

                case "2", "show", "show movies" -> System.out.println(cont.showMovies());

                case "3", "search", "search movie" -> {
                    System.out.println("Enter the title of the movie, you wish to find: ");
                    System.out.println(cont.findMovie(sc.next()));
                }

                case "4", "exit" -> {
                    System.out.println("Exiting your movie collection.");
                    return;
                }
            }
        }
    }

    public void createMovieCase(){
        System.out.println("Please input: Title, director, year created, is in color, length in minutes and genre\n");

        System.out.print("Title: ");
        String movieTitle = sc.next();

        System.out.print("Director: ");
        String movieDirector = sc.next();


        int yearCreated = 0;
        while (true) {
            System.out.print("Year created: ");
            if (sc.hasNextInt()) {
                yearCreated = sc.nextInt();
                if (yearCreated > 0) {
                    break;
                } else {
                    System.out.println("Invalid input. Year must be a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid year.");
                sc.next();
            }
        }

        System.out.print("Is the movie in color (yes/no): ");
        boolean inColor = false;
        boolean validInput = false;
        while (!validInput) { //måske put i en seperat metode
            String isInColor = sc.next();
            if (isInColor.equalsIgnoreCase("no")) {
                validInput = true;
            } else if (isInColor.equalsIgnoreCase("yes")) {
                inColor = true;
                validInput = true;
            } else {
                System.out.println("Invalid input, please write 'yes' or 'no'");
            }
        }

        int lengthInMinutes = 0;
        while (true) {
            System.out.print("Length in minutes: ");
            if (sc.hasNextInt()) {
                lengthInMinutes = sc.nextInt();
                if (lengthInMinutes > 0) {
                    break;
                } else {
                    System.out.println("Invalid input. Length must be a positive number.");
                }

            } else {
                System.out.println("Invalid input. Please enter a valid length.");
                sc.next();
            }
        }

        System.out.print("Genre: ");
        String genre = sc.next();

        cont.addMovie(movieTitle, movieDirector, yearCreated, inColor, lengthInMinutes, genre);
    }
}

