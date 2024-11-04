import java.util.ArrayList;

public class Controller {

    private MovieCollection movieCollection = new MovieCollection();
    private FileHandler fh = new FileHandler();

    public void addMovie (String titel, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieCollection.addMovie(titel, director, yearCreated, isInColor, lengthInMinutes, genre);
    }

    public String showMovies(){
        return movieCollection.showMovies(movieCollection.getMovies());
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

    public void saveMovies() {
        ArrayList<Movie> currentMovies = fh.loadFromFile(); // Load existing movies from the file
        ArrayList<Movie> newMovies = movieCollection.getMovies(); // Get movies from the current session
        // Merge the new movies with current movies, avoiding duplicates
        for (Movie movie : newMovies) {
            if (!currentMovies.contains(movie)) {
                currentMovies.add(movie);
            }
        }
        // Save the merged list back to the file
        fh.saveToFile(currentMovies);
    }

    public void sortMoviesByTitle() {
        movieCollection.sortMoviesByTitle();
    }

    public void sortMoviesByYear() {
        movieCollection.sortMoviesByYear();
    }

    public void sortMoviesByLengthInMinutes() {
        movieCollection.sortMoviesByLengthInMinutes();
    }
}
