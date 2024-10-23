import java.util.ArrayList;

public class MovieCollection {

    private ArrayList<Movie> listOfMovies;

    MovieCollection() {
        listOfMovies = new ArrayList<>();
    }

    public void addMovie (Movie movie) {
        listOfMovies.add(movie);
    }


}
