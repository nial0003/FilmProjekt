import java.util.ArrayList;
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
                    4. Edit movie
                    5. Exit
                    """);
            String input = sc.nextLine();
            switch (input) {
                case "1", "create", "create movie" -> createMovieCase();

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
                    if (moreThanOneMovie.isEmpty()){
                        System.out.println("no such movie exist on our list of movies.");
                    }

                    //is there more than one movie. If there is ask user to pick one of the movies
                    if (moreThanOneMovie.size() > 1){
                        int x = 1;
                        for (Movie movie : moreThanOneMovie){
                            System.out.println(x + ") " + movie.getTitle());
                            x++;
                        }
                        System.out.println("Please enter the number of the movie you wish to edit: ");
                        int userChoice = sc.nextInt() - 1;
                        sc.nextLine();

                        //If userchoice is more than the size of the list of movies or less than 0
                        //Continues to prompt the user for a valid number.
                        while (userChoice>moreThanOneMovie.size() || userChoice < 0) {
                            System.out.println("Entered number is not on the list of movies!");
                            x = 1;
                            for (Movie movie : moreThanOneMovie){
                                System.out.println(x + ") " + movie.getTitle());
                            }
                            System.out.println("Please enter the number of the movie you wish to edit: ");
                            userChoice = sc.nextInt() - 1;
                            sc.nextLine();
                        }

                        Movie chosenMovie = moreThanOneMovie.get(userChoice);
                        System.out.println("Do you wish to edit the title, director, year created, is in color, length or genre?");
                        String partToEdit = sc.nextLine();
                        switch (partToEdit){
                            case "title"-> {
                                System.out.println("current title is: " + chosenMovie.getTitle());
                                System.out.println("Enter new title of the movie: ");
                                chosenMovie.setTitle(sc.nextLine());
                            }
                            case "director"->{
                                System.out.println("current director is: " +chosenMovie.getDirector());
                                System.out.println("Enter new director of the movie: ");
                                chosenMovie.setDirector(sc.nextLine());
                            }
                            case "year created" ->{
                                System.out.println("current year created is: " +chosenMovie.getYearCreated());
                                System.out.println("Enter new year created of the movie: ");
                                chosenMovie.setYearCreated(sc.nextInt());
                            }
                            case "is in color" ->{
                                System.out.println("current is in color is: " + chosenMovie.isInColor());
                                if (chosenMovie.isInColor()){
                                    chosenMovie.setInColor(false);
                                } else {
                                    chosenMovie.setInColor(true);
                                }
                                System.out.println("current is in color has been set to: " + chosenMovie.isInColor());
                            }
                            case "length" -> {
                                System.out.println("current length in minutes is: " +chosenMovie.getLengthInMinutes());
                                System.out.println("Enter new length in minutes of the movie: ");
                                chosenMovie.setLengthInMinutes(sc.nextInt());
                            }
                            case "genre" -> {
                                System.out.println("current genre is: " + chosenMovie.getGenre());
                                System.out.println("Enter new genre of the movie: ");
                                chosenMovie.setGenre(sc.nextLine());
                            }
                        }
                    }
                    //if only one movie exist ask user what they wish to change.
                    else {
                        Movie chosenMovie = moreThanOneMovie.getFirst();
                        System.out.println("Do you wish to edit the title, director, year created, is in color, length or genre?");
                        String partToEdit = sc.nextLine();
                        switch (partToEdit){
                            case "title"-> {
                                System.out.println("current title is: " + chosenMovie.getTitle());
                                System.out.println("Enter new title of the movie: ");
                                chosenMovie.setTitle(sc.nextLine());
                            }
                            case "director"->{
                                System.out.println("current director is: " +chosenMovie.getDirector());
                                System.out.println("Enter new director of the movie: ");
                                chosenMovie.setDirector(sc.nextLine());
                            }
                            case "year created" ->{
                                System.out.println("current year created is: " + chosenMovie.getYearCreated());
                                System.out.println("Enter new year created of the movie: ");
//                                Scanner sc1 = new Scanner(System.in);
//                                int y = sc.nextInt();
                                chosenMovie.setYearCreated(sc.nextInt());
                            }
                            case "is in color" ->{
                                System.out.println("current is in color is: " + chosenMovie.isInColor());
                                if (chosenMovie.isInColor()){
                                    chosenMovie.setInColor(false);
                                } else {
                                    chosenMovie.setInColor(true);
                                }
                                System.out.println("current is in color has been set to: " + chosenMovie.isInColor());
                            }
                            case "length" -> {
                                System.out.println("current length in minutes is: " +chosenMovie.getLengthInMinutes());
                                System.out.println("Enter new length in minutes of the movie: ");
                                chosenMovie.setLengthInMinutes(sc.nextInt());
                            }
                            case "genre" -> {
                                System.out.println("current genre is: " + chosenMovie.getGenre());
                                System.out.println("Enter new genre of the movie: ");
                                chosenMovie.setGenre(sc.nextLine());
                            }
                        }
                    }
//                    System.out.println(cont.editMovie(sc.next()));
                }

                case "5", "exit" -> {
                    System.out.println("Exiting your movie collection.");
                    return;
                }
            }
            sc.nextLine();
        }
    }


    public void createMovieCase(){
        System.out.println("Please input: Title, director, year created, is in color, length in minutes and genre\n");

        System.out.print("Title: ");
        String movieTitle = sc.nextLine();

        System.out.print("Director: ");
        String movieDirector = sc.nextLine();


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
                sc.nextLine();
            }
        }

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
                sc.nextLine();
            }
        }

        System.out.print("Genre: ");
        String genre = sc.nextLine();

        cont.addMovie(movieTitle, movieDirector, yearCreated, inColor, lengthInMinutes, genre);
    }
}

