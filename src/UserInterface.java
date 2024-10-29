import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    Controller cont = new Controller();
    Scanner sc = new Scanner(System.in);

    public void startProgram() {

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
                    4. Edit movie
                    5. Exit
                    """);
            String input = sc.nextLine();
            switch (input.toLowerCase()) {
                case "1", "create", "create movie" -> {
                    createMovieCase();
                    while (true) {
                        System.out.println("Do you wish to creat another movie yes/no?");
                        if (sc.nextLine().equalsIgnoreCase("yes")) {
                            createMovieCase();
                        } else {
                            break;
                        }
                    }
                }

                case "2", "show", "show movies" -> System.out.println(cont.showMovies());

                case "3", "search", "search movie" -> {
                    System.out.println("Enter the title of the movie, you wish to find: ");
                    System.out.println(cont.findMovie(sc.nextLine()));
                }

                case "4", "edit", "edit movie" -> {
                    System.out.println("Enter the title of the movie you wish to edit: ");
                    //Does the movie exist. If not print "movie does not exist"
                    ArrayList<Movie> moreThanOneMovie = new ArrayList<>();
                    moreThanOneMovie = cont.findMovieObject(sc.nextLine(), moreThanOneMovie);

                    if (moreThanOneMovie.isEmpty()) {
                        System.out.println("no such movie exist on our list of movies.");
                        continue;
                    }

                    //is there more than one movie. If there is ask user to pick one of the movies
                    if (moreThanOneMovie.size() > 1) {
                        int x = 1;
                        for (Movie movie : moreThanOneMovie) {
                            System.out.println(x + ") " + movie.getTitle());
                            x++;
                        }
                        System.out.println("Please enter the number of the movie you wish to edit: ");
                        int userChoice = sc.nextInt() - 1;
                        sc.nextLine();

                        //If userchoice is more than the size of the list of movies or less than 0
                        //Continues to prompt the user for a valid number.
                        while (userChoice > moreThanOneMovie.size() || userChoice < 0) {
                            System.out.println("Entered number is not on the list of movies!");
                            x = 1;
                            for (Movie movie : moreThanOneMovie) {
                                System.out.println(x + ") " + movie.getTitle());
                            }
                            System.out.println("Please enter the number of the movie you wish to edit: ");
                            userChoice = sc.nextInt() - 1;
                            sc.nextLine();
                        }

                        Movie chosenMovie = moreThanOneMovie.get(userChoice);
                        editMovieProperties(chosenMovie);
                    }
                    //if only one movie exist ask user what they wish to change.
                    else {
                        Movie chosenMovie = moreThanOneMovie.getFirst();
                        editMovieProperties(chosenMovie);
                    }
                }

                case "5", "exit" -> {
                    System.out.println("Exiting your movie collection.");
                    return;
                }
            }
        }
    }

    public int validIntCheck(boolean isInteger, String input) {
        while (!isInteger) {
            sc.nextLine();
            System.out.println("Invalid " + input + ". Please enter a valid " + input + ".");
            if (sc.hasNextInt()) {
                isInteger = true;
            } else {
                sc.next();
            }
        }
        return sc.nextInt();
    }


    public void createMovieCase() {
        System.out.println("Please input: Title, director, year created, is in color, length in minutes and genre\n");

        System.out.print("Title: ");
        String movieTitle = checkValidString(sc.nextLine());

        System.out.print("Director: ");
        String movieDirector = checkValidString(sc.nextLine());

        System.out.println("Year created: ");
        int yearCreated = validIntCheck(sc.hasNextInt(), "Year created");
        sc.nextLine();

        System.out.print("Is the movie in color (yes/no): ");
        boolean inColor = false;
        boolean validInput = false;
        while (!validInput) { //måske put i en seperat metode
            String isInColor = sc.nextLine();
            if (isInColor.equalsIgnoreCase("no")) {
                validInput = true;
            } else if (isInColor.equalsIgnoreCase("yes")) {
                inColor = true;
                validInput = true;
            } else {
                System.out.println("Invalid input, please write 'yes' or 'no'");
            }
        }

        System.out.println("Length in minutes: ");
        int lengthInMinutes = validIntCheck(sc.hasNextInt(), "Length in minutes");

        sc.nextLine();
        System.out.print("Genre: ");
        String genre = checkValidString(sc.nextLine());

        cont.addMovie(movieTitle, movieDirector, yearCreated, inColor, lengthInMinutes, genre);
    }

    private String checkValidString(String input) {
        while (input.isEmpty()) {
            System.out.println("Please enter a non-empty string value");
            input = sc.nextLine();
        }
        return input;
    }

    private void editMovieProperties(Movie movie) {
        while (true) {
            System.out.print("What would you like to edit (title, director, year created, is in color, length, genre)? ");
            String partToEdit = sc.nextLine();

            System.out.print("Enter the new value for " + partToEdit + ": ");
            String newValue = sc.nextLine();

            // Call editProperty and print the result message
            String resultMessage = cont.editProperty(partToEdit, newValue, movie);
            System.out.println(resultMessage);

            System.out.print("Do you wish to edit another property? (yes/no): ");
            if (!sc.nextLine().equalsIgnoreCase("yes")) break;
        }
    }
}

