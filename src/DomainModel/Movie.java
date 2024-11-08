package DomainModel;

import java.io.Serial;
import java.io.Serializable;

public class Movie implements Serializable {

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

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        String s = "";
        if (isInColor) {
            s = "Yes";
        } else {
            s = "No";
        }

        return String.format("%s \n\tDirector: %s \n\tYear created: %d \n\tIn color: %s \n\tLength in minutes: %d \n\tGenre: %s \n"
                , title, director, yearCreated, s, lengthInMinutes, genre);
    }

    public String editProperty(String partToEdit, String newValue) {
        switch (partToEdit.toLowerCase()) {
            case "title" -> {
                String title = firstLetterToUpper(newValue);
                setTitle(title);
                return "Title has been changed to " + title;
            }
            case "director" -> {
                String director = firstLetterToUpper(newValue);
                setDirector(director);
                return "Director has been changed to " + director;
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
                String genre = firstLetterToUpper(newValue);
                setGenre(genre);
                return "Genre has been changed to " + genre;
            }
            default -> {
                return "No such part exist to edit.";
            }
        }
    }

    public String toCSVStyle() {
        return "\n" + title + "," + director + "," + yearCreated + "," + isInColor + "," + lengthInMinutes + "," + genre;
    }

    public static String firstLetterToUpper(String string) {
        string = string.toLowerCase();
        String finalString = "";
        if (string.contains(" ")) {
            String[] strings = string.split("\\s+");
            for (String strs : strings) {
                String s = strs.substring(0, 1).toUpperCase() + strs.substring(1);
                finalString += s + " ";
            }
            return finalString;
        } else {
            return string.substring(0, 1).toUpperCase() + string.substring(1);
        }
    }
}
