/**
 * Store the Movies
 * 
 * @author DJ
 * @version 1
 */
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import ecs100.*;

public class MovieDatabase 
{
    // instance variables
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
        try{
            // create new movies.txt if it does not exist and then open with scanner
            myfile.createNewFile();
            Scanner scan = new Scanner(myfile);
            // iterate through lines to add to add movie
            int lineNumber = 0;
            while(scan.hasNextLine()){
                // read line and split data into pieces in array
                String line = scan.nextLine();
                String[] arr = line.split("(?=,)");
                // call create movie function with data from array
                this.createMovie(arr[0].substring(1), arr[1].substring(1), arr[2].substring(1), Integer.parseInt(arr[3].substring(1)));
                lineNumber++;
            }
            scan.close();
        } 
        catch(IOException e){
            return;
        }
    }
    
    /** 
     * Saves movies to file
     */
    public void saveMovies(){
        File myfile = new File("movies.txt");
        try{
            FileWriter myWriter = new FileWriter("movies.txt");
            for (int i = 0; i < movieList.size(); i++){
               myWriter.write("," + movieList.get(i).getMovieTitle() + "," + movieList.get(i).getDirector() + "," + movieList.get(i).getGenre() + "," + movieList.get(i).getRating() + String.format("%n"));
            }
            myWriter.close();
        }
        catch(IOException e){
            return;
        }
    }
    
    /**
     * Return all movies to GUI class
     * @return movieArray   Array of movie titles
     */
    public String[] getAllMovies(){
        String[] movieArray = new String[movieList.size()];
        for (int i = 0; i < movieList.size(); i++){
            movieArray[i] = movieList.get(i).getMovieTitle();
        }
        return movieArray;
    }
    
    /**
     * Add movie to array
     * @param   String movieTitle   Name of movie
     * @param   String genre        Movie genre
     * @param   String director     Director of movie
     * @param   int rating          Rating of movie in stars
     */
    public void createMovie(String movieTitle,
                        String genre,
                        String director,
                        int rating)
    {
        movieList.put(storedMovies, new Movie(movieTitle, genre, director, rating));
        storedMovies++;
        this.saveMovies();
        // return error if stored movie has same name soon
    }
    
    /** 
     * Get ID of Movie object based on title
     */
    public int returnMovieID(String titleQuery){
        // set returned movie num to -1 in case no movie is found
        int movieNum = -1;
        int resultsCount = 0;
        for (int i = 0; i < movieList.size(); i++){
            if ( movieList.get(i).getMovieTitle().toLowerCase().equals(titleQuery.toLowerCase().trim())){
                movieNum = i;
                resultsCount++;
            }
        }
        return movieNum;
    }
    
    // make class that loops through all movies checking for highest rating, when finding a higher rating,
    // check if the genre is the same or check if the director is the same
    public String[] getTopRecommended(int movieID){
        String[] movieArray = new String[2];
        for (int i = 0; i < 2; i++){
            movieArray[i] = "null";
        }
        int resultsCount = 0;
        for (int i = 0; i < movieList.size(); i++){
            if ( movieID != i && movieList.get(i).getRating() >= 4){
                if (resultsCount < 2){
                    movieArray[resultsCount] = movieList.get(i).getMovieTitle();
                    resultsCount++;
                }
            }
        }
        return movieArray;
    }
    
    /**
     * Get name of Movie object inside of movieList HashMap
     * @param   int movieID  ID in array of selected movie
     * @return  String movieTitle
     */
    public String returnMovieTitle(int movieID)
    {
        String movieTitle = movieList.get(movieID).getMovieTitle();
        return movieTitle;
    }
    
    /**
     * Get director of Movie object inside of movieList HashMap
     * @param   int movieID  ID in array of selected movie
     * @return  String movieDirector
     */
    public String returnMovieDirector(int movieID)
    {
        String movieDirector = movieList.get(movieID).getDirector();
        return movieDirector;
    }
    
    /**
     * Get genre of Movie object inside of movieList HashMap
     * @param   int movieID  ID in array of selected movie
     * @return  String movieGenre
     */
    public String returnMovieGenre(int movieID)
    {
        String movieGenre = movieList.get(movieID).getGenre();
        return movieGenre;
    }
    
    /**
     * Get rating of Movie object inside of movieList HashMap
     * @param   int movieID  ID in array of selected movie
     * @return  String movieRating
     */
    public int returnMovieRating(int movieID)
    {
        int movieRating = movieList.get(movieID).getRating();
        return movieRating;
    }
}
