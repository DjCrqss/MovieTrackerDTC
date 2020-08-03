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
        String genre = UI.askString("Movie genre: ").trim();int rating = 0;
        boolean askAgain = true;
        while (askAgain == true){
            
            try{
                rating = UI.askInt("Your rating (0-5): ");
                if (rating < 0 || rating > 5){
                    throw new ArithmeticException();
                } else{
                    askAgain = false;
                }
            }
            catch(ArithmeticException ae){
                UI.println("Your rating (0-5):  (must be between 0-5): ");
            }
        }
        database1.createMovie(title, genre, director, rating);
        UI.println("Added " + title + " to the movie list.");
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
        
        // DRAW GUI
        //UI.setColor(color.Blue);
        UI.fillRect(50, 50, 250, 200);
        UI.setFontSize(18);
        UI.setColor(Color.darkGray);
        UI.drawString(database1.returnMovieTitle(movieID), 50, 275);
        UI.drawString(database1.returnMovieDirector(movieID), 50, 300);
        UI.drawString(database1.returnMovieGenre(movieID), 50, 325);
        for (int i = 0; i < database1.returnMovieRating(movieID); i++){
            UI.fillOval(50 + (22 * i), 350, 20, 20);
        }
        for (int i = 5; i > database1.returnMovieRating(movieID); i--){
            UI.drawOval(50 + (database1.returnMovieRating(movieID) * 22) + (22 * (5-i)), 350, 20, 20);
        }
    }

    /**
     * Main routine
     * Setup GUI
     */
    //public static void main(String[] args){
    public void main(){
        GUI obj = new GUI();  // Make a GUI object
        
        UI.initialise();
        UI.setWindowSize(700, 500);
        UI.addButton("Add Movie", obj::addMovie);
        UI.addButton("Search Movie", obj::searchMovie);
        UI.addButton("Display Movies", obj::displayMovies);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.5);     // must come after setting up buttons etc.
    }
 
    private void clearScreen(){
        UI.clearGraphics();
    }
}

