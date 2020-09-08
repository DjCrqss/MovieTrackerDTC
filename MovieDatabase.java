// import functions
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import ecs100.*;
/**
 * This class is for movie objects that stores the movie information
 * 
 * @author DJ
 * @version 1
 */
public class MovieDatabase 
{
    // create instance hashmap and set stored movies to 0
    private HashMap<Integer, Movie> movieList;
    private int storedMovies = 0;
    
    /**
     * Constructor for objects of class MovieDatabase
     */
    public MovieDatabase()
    {
        // initialise instance variables
        movieList = new HashMap<Integer, Movie>();
        // open file
        File myfile = new File("movies.txt");
        try {
            // create new movies.txt if it does not exist then open with scanner
            myfile.createNewFile();
            Scanner scan = new Scanner(myfile);
            // iterate through lines to add to add movie
            int lineNumber = 0;
            while (scan.hasNextLine()) {
                // read line and split data into pieces in array
                String line = scan.nextLine();
                String[] arr = line.split("(?=,)");
                // call create movie function with data from array
                this.createMovie(arr[0].substring(1),
                                arr[1].substring(1),
                                arr[2].substring(1),
                                Integer.parseInt(arr[3].substring(1)));
                lineNumber++;
            }
            // close file
            scan.close();
        }
        // catch error and return to program
        catch (IOException e) {
            return;
        }
    }
    
    /** 
     * Saves movies to file
     */
    public void saveMovies() {
        // open movie txt file
        File myfile = new File("movies.txt");
        try {
            // overwrite file with values from HashMap
            FileWriter myWriter = new FileWriter("movies.txt");
            for (int i = 0; i < movieList.size(); i++) {
                myWriter.write("," + movieList.get(i).getMovieTitle() + ","
                                + movieList.get(i).getDirector() + ","
                                + movieList.get(i).getGenre() + ","
                                + movieList.get(i).getRating()
                                + String.format("%n"));
            }
            myWriter.close();
        }
        // catch error and return to program
        catch (IOException e) {
            return;
        }
    }
    
    /**
     * Return all movies to GUI class
     * @return movieArray   Array of movie titles
     */
    public String[] getAllMovies() {
        // create array size of total movie amount and iterate through to add
        String[] movieArray = new String[movieList.size()];
        for (int i = 0; i < movieList.size(); i++) {
            movieArray[i] = movieList.get(i).getMovieTitle();
        }
        return movieArray;
    }
    
    /**
     * Add movie to array
     * @param   movieTitle   String Name of movie
     * @param   genre        String Movie genre
     * @param   director     String Director of movie
     * @param   rating       int Rating of movie in stars
     */
    public void createMovie(String movieTitle,
                        String genre,
                        String director,
                        int rating)
    {
        // add object with info to HashMap
        movieList.put(storedMovies, new Movie(movieTitle,
                            genre,
                            director,
                            rating));
        // increase counter and call function to save movie file
        storedMovies++;
        this.saveMovies();
    }
    
    /** 
     * Get ID of Movie object based on title
     * @param   titleQuery  String name of movie from user input
     * @return  movieNum    int ID of movie in HashMap
     */
    public int returnMovieID(String titleQuery) {
        // set returned movie num to -1 in case no movie is found
        int movieNum = -1;
        int resultsCount = 0;
        // iterate movie array and set movieNum ID to movie with same name
        for (int i = 0; i < movieList.size(); i++) {
            if ( movieList.get(i).getMovieTitle().toLowerCase()
                    .equals(titleQuery.toLowerCase().trim())) {
                movieNum = i;
                resultsCount++;
            }
        }
        return movieNum;
    }
    
    /** 
     * Create list of movies to recommend to user
     * @param   movieID     int ID of movie currently selected
     * @return  movieArray  Array of recommended movie titles
     */
    public String[] getTopRecommended(int movieID) {
        String[] movieArray = new String[2];
        // set both movies to null
        for (int i = 0; i < 2; i++) {
            movieArray[i] = "null";
        }
        int resultsCount = 0;
        // get movies that are the same genre or director if rating is high
        for (int i = 0; i < movieList.size(); i++) {
            if ( movieID != i && (movieList.get(movieID).getRating() >= 3
                && movieList.get(movieID).getGenre()
                .equals(movieList.get(movieID).getGenre())
                || movieList.get(movieID).getDirector()
                .equals(movieList.get(movieID).getDirector()))) {
                if (resultsCount < 2) {
                    movieArray[resultsCount] = movieList.get(i).getMovieTitle();
                    resultsCount++;
                }
            }
        }
        // populate results with highly rated movies otherwise
        for (int i = 0; i < movieList.size(); i++) {
            if ( movieID != i && movieList.get(i).getRating() >= 4) {
                if (resultsCount < 2) {
                    movieArray[resultsCount] = movieList.get(i).getMovieTitle();
                    resultsCount++;
                }
            }
        }
        return movieArray;
    }
    
    /**
     * Get name of Movie object inside of movieList HashMap
     * @param   movieID     int ID in array of selected movie
     * @return  movieTitle  String of name of movie
     */
    public String returnMovieTitle(int movieID)
    {
        String movieTitle = movieList.get(movieID).getMovieTitle();
        return movieTitle;
    }
    
    /**
     * Get director of Movie object inside of movieList HashMap
     * @param   movieID         int ID in array of selected movie
     * @return  movieDirector   String of director of movie
     */
    public String returnMovieDirector(int movieID)
    {
        String movieDirector = movieList.get(movieID).getDirector();
        return movieDirector;
    }
    
    /**
     * Get genre of Movie object inside of movieList HashMap
     * @param   movieID     int ID in array of selected movie
     * @return  movieGenre  String of genre of movie
     */
    public String returnMovieGenre(int movieID)
    {
        String movieGenre = movieList.get(movieID).getGenre();
        return movieGenre;
    }
    
    /**
     * Get rating of Movie object inside of movieList HashMap
     * @param   movieID     int ID in array of selected movie
     * @return  movieRating int rating of movie
     */
    public int returnMovieRating(int movieID)
    {
        int movieRating = movieList.get(movieID).getRating();
        return movieRating;
    }
}
