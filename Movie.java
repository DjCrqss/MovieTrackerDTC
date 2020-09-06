
/**
 * This class is for a movie object that can be retrieved
 * Has movie's name, genre, director and user rating
 * 
 * @author DJ
 * @version 1
 */
public class Movie
{
    // instance variables
    private String movieTitle;
    private String genre;
    private String director;
    private int rating;

    /**
     * Constructor for objects of class Movie
     * @param   movieTitle   String Name of movie
     * @param   genre        String Movie genre
     * @param   director     String Director of movie
     * @param   rating       Int Rating of movie in stars
     */
    public Movie(String movieTitle, String genre, String director, int rating)
    {
        // initialise instance variables
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.director = director;
        this.rating = rating;
    }

    /**
     * Return movie title
     * @return     String movieTitle
     */
    public String getMovieTitle()
    {
        return this.movieTitle;
    }
    
    /**
     * Return movie genre
     * @return     String genre
     */
    public String getGenre()
    {
        return this.genre;
    }
    
    /**
     * Return movie director
     * @return     String director
     */
    public String getDirector()
    {
        return this.director;
    }
    
    /**
     * Return movie rating
     * @return     Int rating
     */
    public int getRating()
    {
        return this.rating;
    }
}
