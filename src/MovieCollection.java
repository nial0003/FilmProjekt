import java.util.ArrayList;

public class MovieCollection {

    private ArrayList<Movie> listOfMovies;

    MovieCollection() {
        listOfMovies = new ArrayList<>();
    }

    public void addMovie (String titel, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        listOfMovies.add(new Movie(titel, director, yearCreated, isInColor, lengthInMinutes, genre));
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
            if (movie.getTitel().toLowerCase().startsWith(input.toLowerCase())){
                s += movie +"\n";
            }
        }
        if (!s.isEmpty()){
            return s;
        } else {
            return "Movie not found.";
        }
    }
}
