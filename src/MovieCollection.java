import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MovieCollection {

    private ArrayList<Movie> listOfMovies;

    MovieCollection() {
        listOfMovies = new ArrayList<>();
    }

    public ArrayList<Movie> getMovies() {
        return listOfMovies;
    }

    public void addMovie (String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        listOfMovies.add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
    }

    public String showMovies (ArrayList<Movie> listOfMoviesFromFile) {
        String s = "";
        for (Movie movie : listOfMoviesFromFile){
            s += movie.toString()+"\n";
        }
        return s;
    }

    public String findMovie (String input) {
        String s ="";
        for (Movie movie : listOfMovies) {
            if (movie.getTitle().toLowerCase().startsWith(input.toLowerCase())){
                s += movie +"\n";
            }
        }
        if (!s.isEmpty()){
            return s;
        } else {
            return "Movie not found.";
        }
    }

    public ArrayList<Movie> findMovieObject(String input){
        ArrayList<Movie> matchedMovies = new ArrayList<>();
        for (Movie movie : listOfMovies){
            if (movie.getTitle().toLowerCase().startsWith(input.toLowerCase())){
                matchedMovies.add(movie);
            }
        }
        return matchedMovies;
    }

    public String removeMovie(Movie movieToBeRemoved){
        String titleOfRemovedMovie = movieToBeRemoved.getTitle();
        listOfMovies.remove(movieToBeRemoved);
        return titleOfRemovedMovie + " has been removed from our list of movies!";
    }

    public ArrayList<Movie> getMovies(ArrayList<Movie> movies) {
        return listOfMovies;
    }

    public void sortMoviesByTitle() {
        Collections.sort(listOfMovies, Comparator.comparing(movie -> {
            String title = movie.getTitle();
            if (title.toLowerCase().startsWith("the ")) {
                return title.substring(4); //skip "the" and start from the next word
            }
            return title;
        }));
    }

    public void sortMoviesByYear() {
        Collections.sort(listOfMovies, Comparator.comparingInt(Movie::getYearCreated));
    }

    public void sortMoviesByLengthInMinutes() {
        Collections.sort(listOfMovies, Comparator.comparingInt(Movie::getLengthInMinutes));
    }
}