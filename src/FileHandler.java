import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private final File file = new File("filmProjekt.txt");


    public ArrayList<Movie> loadFromFile() {
        ArrayList<Movie> listOfMovies = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.isBlank()) {
                    String[] data = line.split(",");
                    String title = data[0];
                    String director = data[1];
                    int yearCreated = Integer.parseInt(data[2]);
                    boolean isInColor = Boolean.parseBoolean(data[3]);
                    int lengthInMinutes = Integer.parseInt(data[4]);
                    String genre = data[5];

                    listOfMovies.add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: filmProjekt.txt", e);
        }
        return listOfMovies;
    }
}