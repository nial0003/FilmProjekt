package DomainModel;

import java.util.ArrayList;


public class MovieCollection {

    private ArrayList<Movie> listOfMovies;

    public MovieCollection() {
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
            return "DomainModel.Movie not found.";
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

    public void clearMovieList(){
        listOfMovies.clear();
    }

    public void setListOfMovies(ArrayList<Movie> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }
}