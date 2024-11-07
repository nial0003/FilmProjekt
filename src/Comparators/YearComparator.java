package Comparators;

import DomainModel.Movie;

import java.util.Comparator;

public class YearComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return Integer.compare(o1.getYearCreated(), o2.getYearCreated());
    }
}