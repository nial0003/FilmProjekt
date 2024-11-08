package UserInterface;

import DomainModel.Controller;
import DomainModel.Movie;

import java.util.*;
import java.time.LocalDate;
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
                        System.out.println("Do you wish to create another movie yes/no?");
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
                    List<Comparator<Movie>> chosenComparators = new ArrayList<>();

                    boolean addMoreSorting = true;
                    while (addMoreSorting) {
                        System.out.println("Choose a sorting criterion:");
                        System.out.println("1) Title A-Z");
                        System.out.println("2) Year Created");
                        System.out.println("3) Length in Minutes");

                        String sortOption = sc.nextLine();
                        switch (sortOption) {
                            case "1" -> chosenComparators.add(cont.getComparatorByTitle());
                            case "2" -> chosenComparators.add(cont.getComparatorByYear());
                            case "3" -> chosenComparators.add(cont.getComparatorByLength());
                            default -> System.out.println("Invalid option. Please choose a valid criterion.");
                        }

                        cont.sortMovies(chosenComparators);
                        System.out.println("Movies sorted by chosen criteria:");
                        System.out.println(cont.showMovies());

                        System.out.println("Would you like to add another sorting criterion? (yes/no)");
                        String response = sc.nextLine();
                        addMoreSorting = response.equalsIgnoreCase("yes");
                    }
                    cont.clearMovieList();
                }


                case "3", "search", "search movie" -> {
                    System.out.println("Enter the title of the movie, you wish to find: ");
                    System.out.println(cont.findMovie(sc.nextLine()));
                }

                case "4", "edit", "edit movie" -> {
                    System.out.println("Enter the title of the movie, you wish to edit: ");
                    //Loads the list of movies from the file into the arrayList in the movie collection
                    cont.setListOfMovies(cont.loadFromFile());

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

                    cont.deleteOldFileAndCreateNewEmptyFile();
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
        int yearCreated = 0;
        int currentYear = LocalDate.now().getYear();
        while (true) {
            try {
                yearCreated = sc.nextInt();
                if (yearCreated >= 1895 && yearCreated <= currentYear) {
                    break;
                } else {
                    System.out.println("Please enter a year between 1895 and " + currentYear + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value for the year.");
                sc.next();
            }
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
        return cont.firstLetterToUpper(input);
    }

    private void editMovieProperties(Movie movie) {
        while (true) {
            System.out.print("What would you like to edit (title, director, year created, is in color, length, genre)?");
            String partToEdit = sc.nextLine();
            String newValue = "";

            if (partToEdit.equalsIgnoreCase("color") || partToEdit.equalsIgnoreCase("is in color")) {
                System.out.print("Enter the new value for " + partToEdit + " yes/no: ");
            } else if (partToEdit.equalsIgnoreCase("year") || partToEdit.equalsIgnoreCase("year created")) {
                int currentYear = LocalDate.now().getYear();
                while (true) {
                    System.out.print("Enter the new value for " + partToEdit);
                    newValue = sc.nextLine();
                    try {
                        int year = Integer.parseInt(newValue);
                        if (year >= 1895 && year <= currentYear) {
                            break; // valid year, exit the loop
                        } else {
                            System.out.println("Invalid year. Please enter a value between 1895 and " + currentYear + ".");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number for the year.");
                    }
                }
            } else {
                System.out.print("Enter the new value for " + partToEdit + ": ");
            }

            // Call editProperty and print the result message
            String resultMessage = cont.editProperty(partToEdit, newValue, movie);
            System.out.println(resultMessage);

            System.out.print("Do you wish to edit another property? (yes/no): ");
            if (!sc.nextLine().equalsIgnoreCase("yes")) break;
        }
    }
}

