import java.util.ArrayList;

public class Controller {

    MovieCollection movieCollection = new MovieCollection();
    Movie movie;

    public void addMovie (String titel, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieCollection.addMovie(titel, director, yearCreated, isInColor, lengthInMinutes, genre);
    }

    public String showMovies(){
        return movieCollection.showMovies();
    }

    public String findMovie (String input) {
        return movieCollection.findMovie(input);
    }

    public ArrayList<Movie> findMovieObject(String input, ArrayList<Movie> moreThanOneMovie){
        return movieCollection.findMovieObject(input);
    }

    public String editProperty(String partToEdit, String newValue, Movie movie){
        return movie.editProperty(partToEdit, newValue);
    }
}
