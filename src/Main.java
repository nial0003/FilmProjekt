public class Main {
    public static void main(String[] args) {


        Movie movie1 = new Movie("Skyfall", "Sam Mendes", 2012, true, 143, "Action");

        MovieCollection listOfMovies = new MovieCollection();

        listOfMovies.addMovie(movie1);

        System.out.println(listOfMovies);


    }

}
