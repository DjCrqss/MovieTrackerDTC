

/**
 * Store the Countries
 * 
 * @author DJ
 * @version 1
 */
import java.util.HashMap;

public class MovieDatabase
{
    // instance variables
    //private Movie[] movieList = new Movie[100]; //stores 100 movies
    private HashMap<Integer, Movie> movieList;
    //private HashMap<String, Country> countryInfections;
    
    private int storedMovies = 0;
    
    /**
     * Constructor for objects of class MovieDatabase
     */
    public MovieDatabase()
    {
        // initialise instance variables
        movieList = new HashMap<Integer, Movie>();
    }
    
    public String[] getAllMovies(){
        String[] movieArray = new String[movieList.size()];
        for (int i = 0; i < movieList.size(); i++){
            movieArray[i] = movieList.get(i).getMovieTitle();
        }
        // create array size of hashmap
        // movieArray[count] = new(x,x,x,x);
        // for loop in hashmap add objects to array
        // return array to GUI which will have a loop for length to print data
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
        /*movieList[storedMovies] = new Movie(movieTitle,
                                            genre,
                                            director,
                                            rating);*/
        storedMovies++;
        // return error if stored movie has same name
    }
    
    /** Get ID of Movie object based on title
     * 
     */
    public int returnMovieID(String titleQuery){
        int movieNum = -1;
        int resultsCount = 0;
        for (int i = 0; i < movieList.size(); i++){
            if ( movieList.get(i).getMovieTitle().toLowerCase().equals(titleQuery.toLowerCase().trim())){
                movieNum = i;
                resultsCount++;
            }
        }
        return movieNum;
        //movieID = id in array
        //return movieID;
        
        
    }
    
    /**
     * Get name of Movie object inside of movieList array
     * @param   int movieID  ID in array of selected movie
     * @return  String movieTitle
     */
    public String returnMovieTitle(int movieID)
    {
        String movieTitle = movieList.get(movieID).getMovieTitle();
        return movieTitle;
    }
    
    public String returnMovieDirector(int movieID)
    {
        String movieDirector = movieList.get(movieID).getDirector();
        return movieDirector;
    }
    
    public String returnMovieGenre(int movieID)
    {
        String movieGenre = movieList.get(movieID).getGenre();
        return movieGenre;
    }
    
    public int returnMovieRating(int movieID)
    {
        int movieRating = movieList.get(movieID).getRating();
        return movieRating;
    }
}
