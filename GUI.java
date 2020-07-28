import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 * Write a description of class Corona here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI
{
    // instance variables - replace the example below with your own
    private MovieDatabase database1 = new MovieDatabase();

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        
    }
    
    
    private void displayMovies(){
        UI.println("\n------ All Movies ------");
        String[] moviesList = database1.getAllMovies();
        for (int i = 0; i < moviesList.length; i++){
             UI.println(moviesList[i]);
        }
    }
    
    
    /**
     * Add a country to the array
     */
    private void addMovie()
    {
        UI.println("\n------  Add Movie  ------");
        String title =  UI.askString("Movie title: ").trim();
        String director = UI.askString("Movie director: ").trim();
        String genre = UI.askString("Movie genre: ").trim();
        int rating = UI.askInt("Your rating (0-5): ");
        database1.createMovie(title, director, genre, rating);
    }
    
    /**
     * Search movie
     */
    private void searchMovie()
    {
        UI.println("\n------ Search Movie ------");
        String titleQuery = UI.askString("Please enter Movie name: ");
        int movieNum = database1.returnMovieID(titleQuery);
        if (movieNum == -1){
            UI.println("There were no movies found with this name");
        }
        else{
            printInfo(movieNum);
        }
        // use function to return list of movies that work with the query
        //UI.println("Title: " + database1.returnMovieTitle(movieNum));
        //UI.println("Director: " + database1.returnMovieDirector(movieNum));
        // UI.println("Genre: " + database1.returnMovieGenre(movieNum));
    }
    
    /**
     * Print info from the array
     */
    private void printInfo(int movieID)
    {
        UI.println("Title: " + database1.returnMovieTitle(movieID));
        UI.println("Director: " + database1.returnMovieDirector(movieID));
        UI.println("Genre: " + database1.returnMovieGenre(movieID));
        UI.print("Rating: ");
        for (int i = 0; i < database1.returnMovieRating(movieID); i++){
            UI.print("★");
        }
        for (int i = 5; i > database1.returnMovieRating(movieID); i--){
            UI.print("☆");
        }
        UI.println();
    }
    
    /**
     * Main routine
     * Setup GUI
     */
    //public static void main(String[] args){
    public void main(){
        GUI obj = new GUI();  // Make a GUI object
        
        UI.initialise();
        UI.setWindowSize(400, 500);
        UI.addButton("Add Movie", obj::addMovie);
        UI.addButton("Search Movie", obj::searchMovie);
        UI.addButton("Display Movies", obj::displayMovies);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(100.0);     // must come after setting up buttons etc.
    }

    
    
    private void clearScreen(){
        UI.clearGraphics();
    }
}

