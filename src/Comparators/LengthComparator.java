package Comparators;

import java.util.Comparator;
import DomainModel.Movie;

public class LengthComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return Integer.compare(o1.getLengthInMinutes(), o2.getLengthInMinutes());
    }
}