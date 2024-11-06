import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private File file = new File("filmProjekt.txt");


    public ArrayList<Movie> loadFromFile() {
        ArrayList<Movie> listOfMovies = new ArrayList<>();

        if (!file.exists() || file.length() == 0) {
            return listOfMovies; // Return an empty list if the file is missing or empty
        }

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

    public void saveToFile(ArrayList<Movie> movies) {
        try (FileWriter writer = new FileWriter(file, true)) {
            for (Movie movie : movies) {
                writer.write(movie.toCSVStyle());
            }
        } catch (IOException e) {
            System.err.println("Error saving movies to file: " + e.getMessage());
        }
    }

    //Deletes the old file and creates a new file of the same name.
    public void deleteOldFileAndCreateNewEmptyFile(){
        if (file.delete()) {
            file = new File("filmProjekt.txt");
        }
    }
}