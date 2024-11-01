import java.util.ArrayList;

public class Controller {

    private MovieCollection movieCollection = new MovieCollection();
    private FileHandler fh = new FileHandler();

    public void addMovie (String titel, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieCollection.addMovie(titel, director, yearCreated, isInColor, lengthInMinutes, genre);
    }

    public String showMovies(){
        return movieCollection.showMovies(fh.loadFromFile());
    }

    public String findMovie (String input) {
        return movieCollection.findMovie(input);
    }

    public ArrayList<Movie> findMovieObject(String input){
        return movieCollection.findMovieObject(input);
    }

    public String editProperty(String partToEdit, String newValue, Movie movie){
        return movie.editProperty(partToEdit, newValue);
    }

    public String removeMovie(Movie movieToBeRemoved){
        return movieCollection.removeMovie(movieToBeRemoved);
    }
}
