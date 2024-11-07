import DomainModel.Movie;
import DomainModel.MovieCollection;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    @Test
    void addMovie() {

        //Arrange - Create a movieCollection (Arraylist), create a movie and add the movie to the movie collection
        ArrayList<Movie> movieCollention = new ArrayList<>();
        Movie movie = new Movie("Skyfall", "Sam Mendes", 2012, true, 143, "Action");
        movieCollention.add(movie);

        //Act - Use
        int actualSize = movieCollention.size();

        //Assert - check if numberOfMovies is as expected (1)
        int expectedResult = 1;
        assertEquals(expectedResult, actualSize);

    }

    @Test
    void addMultipleMovies(){
        //Arrange - Create a movieCollection (Arraylist), create movies and add the movies to the movie collection
        ArrayList<Movie> movieCollention = new ArrayList<>();
        Movie movie = new Movie("Skyfall", "Sam Mendes", 2012, true, 143, "Action");
        movieCollention.add(new Movie("Casablanca", "Michael Curtiz", 1942, false, 102, "Romance/Drama"));
        movieCollention.add(new Movie("Schindler's List", "Steven Spielberg", 1993, true, 195, "Historical/Drama"));
        movieCollention.add(movie);

        //Act -
        int actualSize = movieCollention.size();
        //Assert - check if numberOfMovies is as expected (3)
        int expectedSize = 3;
        assertEquals(expectedSize,actualSize);
    }

    @Test
    void findNoMovieObject(){
        //Arrange -
        MovieCollection movieCollection = new MovieCollection();
        ArrayList<Movie> listOfMovies = movieCollection.findMovieObject("Batman");

        //Act
        int actualSize = listOfMovies.size();

        //Assert
        int expectedSize = 0;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findOneMovie(){
        //Arrange -
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("Skyfall", "Sam Mendes", 2012, true, 143, "Action");

        ArrayList<Movie> listOfMovies = movieCollection.findMovieObject("Skyfall");
        //Act
        int actualSize = listOfMovies.size();

        //Assert
        int expectedSize = 1;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findMoreMovies() {
        //Arrange -
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("Skyfall", "Sam Mendes", 2012, true, 143, "Action");
        movieCollection.addMovie("Schindler's List", "Steven Spielberg", 1993, true, 195, "Historical/Drama");

        ArrayList<Movie> listOfMovies = movieCollection.findMovieObject("S");
        //Act
        int actualSize = listOfMovies.size();

        //Assert
        int expectedSize = 2;
        assertEquals(expectedSize, actualSize);
    }
}