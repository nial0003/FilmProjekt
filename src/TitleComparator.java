import java.util.Comparator;

public class TitleComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        String title1 = o1.getTitle().toLowerCase();
        String title2 = o2.getTitle().toLowerCase();

        if (title1.startsWith("the ")) title1 = title1.substring(4);
        if (title2.startsWith("the ")) title2 = title2.substring(4);

        return title1.compareTo(title2);
    }
}