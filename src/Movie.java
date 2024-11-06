import java.io.Serial;
import java.io.Serializable;

public class Movie implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String title;
    private String director;
    private int yearCreated;
    private boolean isInColor;
    private int lengthInMinutes;
    private String genre;

    public Movie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.isInColor = isInColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public boolean isInColor() {
        return isInColor;
    }

    public void setInColor(boolean inColor) {
        isInColor = inColor;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString (){
        String s = "";
        if (isInColor){
            s = "Yes";
        } else {
            s = "No";
        }

        return String.format("%s \n\tDirector: %s \n\tYear created: %d \n\tIn color: %s \n\tLength in minutes: %d \n\tGenre: %s \n"
                ,title, director, yearCreated, s, lengthInMinutes, genre);
    }

    public String editProperty(String partToEdit, String newValue) {
        switch (partToEdit.toLowerCase()) {
            case "title" -> {
                setTitle(newValue);
                return "Title has been changed to " + newValue;
            }
            case "director" -> {
                setDirector(newValue);
                return "Director has been changed to " + newValue;
            }
            case "year", "year created" -> {
                try {
                    int year = Integer.parseInt(newValue);
                    setYearCreated(year);
                    return "Year created has been changed to " + newValue;
                } catch (NumberFormatException e) {
                    return "Invalid year format. Please enter a valid integer.";
                }
            }
            case "color", "is in color" -> {
                boolean inColor = newValue.equalsIgnoreCase("yes");
                setInColor(inColor);
                return "Is in color has been changed to " + (inColor ? "yes" : "no");
            }
            case "length", "length in minutes" -> {
                try {
                    int length = Integer.parseInt(newValue);
                    setLengthInMinutes(length);
                    return "Length in minutes has been changed to " + newValue;
                } catch (NumberFormatException e) {
                    return "Invalid length format. Please enter a valid integer.";
                }
            }
            case "genre" -> {
                setGenre(newValue);
                return "Genre has been changed to " + newValue;
            }
            default -> {
                return "No such part exist to edit.";
            }
        }
    }

    public String toCSVStyle() {
        return "\n"+title + "," + director + "," + yearCreated + "," + isInColor + "," + lengthInMinutes + "," + genre;
    }
}
