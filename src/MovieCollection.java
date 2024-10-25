import java.util.ArrayList;
import java.util.Locale;

public class MovieCollection {

    private ArrayList<Movie> listOfMovies;

    MovieCollection() {
        listOfMovies = new ArrayList<>();
    }

    public void addMovie (String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        listOfMovies.add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
    }

    public String showMovies () {
        String s = "";
        for (Movie movie : listOfMovies){
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

    public String editMovie(String input){
        ArrayList<Movie> moreThanOneMovie = new ArrayList<>();
        moreThanOneMovie = findMovieObject(input);
        if (moreThanOneMovie.isEmpty()){
            return "No such movie exists on the list.";
        }

        //if more than one movie was found, prints a list of the movies found.
        if (moreThanOneMovie.size() > 1){
            String s = "";
            for (Movie movie : moreThanOneMovie){
                s += movie.getTitle() + "\n";
            }
            return "More than one movie was found: \n"+s;
        }



        return "";
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

    public ArrayList<Movie> getListOfMovies() {
        return listOfMovies;
    }
}
