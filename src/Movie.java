public class Movie {

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

    public String getDirector() {
        return director;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public boolean isInColor() {
        return isInColor;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString (){
        String s = "";
        if (isInColor){
            s = "yes";
        } else {
            s = "no";
        }

        return String.format("%s \n\tDirector: %s \n\tYear created: %d \n\tIn color: %s \n\tLength in minutes: %d \n\tGenre: %s \n"
                ,title, director, yearCreated, s, lengthInMinutes, genre);
    }
}
