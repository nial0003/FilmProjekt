import java.util.ArrayList;

public class Controller {

    MovieCollection movieCollection = new MovieCollection();

    public void addMovie (String titel, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieCollection.addMovie(titel, director, yearCreated, isInColor, lengthInMinutes, genre);
    }

    public String showMovies(){
        return movieCollection.showMovies();
    }

    public String findMovie (String input) {
        return movieCollection.findMovie(input);
    }

    public String editMovie(String input){
        return movieCollection.editMovie(input);
    }

    public ArrayList<Movie> findMovieObject(String input, ArrayList<Movie> moreThanOneMovie){
        return movieCollection.findMovieObject(input, moreThanOneMovie);
    }
}
