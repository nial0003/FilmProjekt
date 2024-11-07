import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private Controller cont = new Controller();
    Scanner sc = new Scanner(System.in);

    public void startProgram() {

//welcome-message string art
        String art = """
                █ ▀ █                                                                       █ ▀ █
                █ ▄ █                                                                       █ ▄ █
                █ ▀ █                                                                       █ ▀ █
                █ ▄ █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ ▄ █
                █ ▀ █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ ▀ █
                █ ▄ █  ▄▀                             ▄                                     █ ▄ █
                █ ▀ █ ▀                               ██▄                                   █ ▀ █
                █ ▄ █                                 ████▄                                 █ ▄ █
                █ ▀ █                                 ███▀                                  █ ▀ █
                █ ▄ █                                 █▀                               ▄▀   █ ▄ █
                █ ▀ █                                                                 ▀     █ ▀ █
                █ ▄ █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ ▄ █
                █ ▀ █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ ▀ █
                █ ▄ █                   WELCOME TO YOUR MOVIE COLLECTION                    █ ▄ █
                ▀ ▀ ▀                                                                       ▀ ▀ ▀
                """;


//print of string art
        System.out.println(art);

        while (true) {
            System.out.println("""
                    1. Create movie
                    2. Show movies
                    3. Search movie
                    4. Edit movie
                    5. Remove movie
                    6. Exit
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
                            cont.saveMovies();
                            break;
                        }
                    }
                }

                case "2", "show", "show movies" -> {
                    cont.setListOfMovies(cont.loadFromFile());
                    System.out.println("Please enter the number of how you wish to sort the movies: ");
                    System.out.println("1) title a-z");
                    System.out.println("2) year created");
                    System.out.println("3) length");
                    String sortOption = sc.nextLine();
                    switch (sortOption) {
                        case "1" -> {
                            cont.sortMoviesByTitle();
                            System.out.println(cont.showMovies());
                        }
                        case "2" -> {
                            cont.sortMoviesByYear();
                            System.out.println(cont.showMovies());
                        }
                        case "3" -> {
                            cont.sortMoviesByLengthInMinutes();
                            System.out.println(cont.showMovies());
                        }
                        default -> {
                            System.out.println("Invalid option. Showing unsorted list.");
                            System.out.println(cont.showMovies());
                        }
                    }
                    cont.clearMovieList();
                }


                case "3", "search", "search movie" -> {
                    System.out.println("Enter the title of the movie, you wish to find: ");
                    System.out.println(cont.findMovie(sc.nextLine()));
                }

                case "4", "edit", "edit movie" -> {
                    System.out.println("Enter the title of the movie you wish to edit: ");
                    //Loads the list of movies from the file into the arrayList in the movie collection
                    cont.setListOfMovies(cont.loadFromFile());
                    cont.deleteOldFileAndCreateNewEmptyFile();

                    //Does the movie exist. If not print "movie does not exist"
                    ArrayList<Movie> moreThanOneMovie = cont.findMovieObject(sc.nextLine());

                    if (moreThanOneMovie.isEmpty()) {
                        System.out.println("no such movie exist on our list of movies.");
                        cont.saveMovies();
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

                    //Saves the arrayList of movies with the edited movie onto the newly created file.
                    cont.saveMovies();
                }

                case "5", "remove", "remove movie" -> {
                    cont.setListOfMovies(cont.loadFromFile());
                    cont.deleteOldFileAndCreateNewEmptyFile();

                    System.out.println("Please pick a movie to remove.");
                    ArrayList<Movie> moreThanOneMovie = cont.findMovieObject(sc.nextLine());

                    if (moreThanOneMovie.isEmpty()) {
                        System.out.println("no such movie exist on our list of movies.");
                        continue;
                    }

                    if (moreThanOneMovie.size() > 1) {
                        int x = 1;
                        for (Movie movie : moreThanOneMovie) {
                            System.out.println(x + ") " + movie.getTitle());
                            x++;
                        }
                        System.out.println("Please enter the number of the movie you wish to remove: ");
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
                            System.out.println("Please enter the number of the movie you wish to remove: ");
                            userChoice = sc.nextInt() - 1;
                            sc.nextLine();
                        }

                        Movie chosenMovie = moreThanOneMovie.get(userChoice);
                        System.out.println(cont.removeMovie(chosenMovie));
                        System.out.println(cont.showMovies());
                    }
                    //if only one movie exist ask user what they wish to remove.
                    else {
                        Movie chosenMovie = moreThanOneMovie.getFirst();
                        System.out.println(cont.removeMovie(chosenMovie));
                        System.out.println(cont.showMovies());
                    }

                    cont.saveMovies();
                }

                case "6", "exit" -> {
                    //cont.saveMovies();
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
        int yearCreated;
        try {
            yearCreated = sc.nextInt();
        } catch (InputMismatchException e) {
            boolean isInteger = false;
            yearCreated = validIntCheck(isInteger, "Year created");
        }
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
                System.out.print("Invalid input, please write 'yes' or 'no'");
            }
        }

        System.out.println("Length in minutes: ");
        int lengthInMinutes;
        try {
            lengthInMinutes = sc.nextInt();
        } catch (InputMismatchException e) {
            boolean isInteger = false;
            lengthInMinutes = validIntCheck(isInteger, "Year created");
        }

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
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    private void editMovieProperties(Movie movie) {
        while (true) {
            System.out.print("What would you like to edit (title, director, year created, is in color, length, genre)?");
            String partToEdit = sc.nextLine();

            if (partToEdit.equalsIgnoreCase("color") || partToEdit.equalsIgnoreCase("is in color")) {
                System.out.print("Enter the new value for " + partToEdit + " yes/no: ");
            } else {
                System.out.print("Enter the new value for " + partToEdit + ": ");
            }
            String newValue = sc.nextLine();

            // Call editProperty and print the result message
            String resultMessage = cont.editProperty(partToEdit, newValue, movie);
            System.out.println(resultMessage);

            System.out.print("Do you wish to edit another property? (yes/no): ");
            if (!sc.nextLine().equalsIgnoreCase("yes")) break;
        }
    }
}

