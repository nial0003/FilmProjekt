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
        ArrayList<Movie> newMovies = movieCollection.getMovies(); // Get movies from the current session
        fh.saveToFile(newMovies);
        movieCollection.clearMovieList();
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

    public ArrayList<Movie> loadFromFile(){
        return fh.loadFromFile();
    }

    public void setListOfMovies(ArrayList<Movie> listOfMovies){
        movieCollection.setListOfMovies(listOfMovies);
    }

    public void clearMovieList(){
        movieCollection.clearMovieList();
    }

    public void deleteOldFileAndCreateNewEmptyFile(){
        fh.deleteOldFileAndCreateNewEmptyFile();
    }
}
