import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 * Write a description of class GUI here.
 * 
 * @author DJ
 * @version 2.1
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
    
    /**
     * Add a movie to the array
     */
    private void addMovie()
    {
        UI.clearGraphics();
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
        //String title1 = capitaliseSentence(title);
        database1.createMovie(capitaliseSentence(title), capitaliseSentence(genre), capitaliseSentence(director), rating);
        UI.println("Added " + title + " to the movie list.");
    }
    
    /**
     * Capitalise the first letter of each word in a string
     * @param String sentence to convert
     * @return String converted sentence
     */
    private String capitaliseSentence(String sentence)
    {
        // split into words
        String[] words = sentence.split(" ");
        // capitalize each word
        for (int i = 0; i < words.length; i++)
        {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        // rejoin back into a sentence
        sentence = String.join(" ", words);
        return sentence;
    }
    
    /**
     * Search movie and get ID
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
    }
    
    /**
     * Print info of movie
     * 
     * 
     */
    private void printInfo(int movieID)
    {   
        UI.clearGraphics();
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
        // MAKE SURE TO DRAW A TITLE
        // DRAW GUI
        UI.setColor(Color.darkGray);
        UI.fillRect(50, 50, 250, 250);
        UI.drawImage("movie_icon.png", 75, 75, 200, 200);
        UI.setFontSize(18);
        // Draw text
        UI.drawString("Movie    | " + database1.returnMovieTitle(movieID), 50, 325);
        UI.drawString("Director | " + database1.returnMovieDirector(movieID), 50, 350);
        UI.drawString("Genre    | " + database1.returnMovieGenre(movieID), 48, 375);
        UI.drawString("Rating   | ", 51, 400);
        for (int i = 0; i < database1.returnMovieRating(movieID); i++){
            UI.fillOval(130 + (22 * i), 385, 20, 20);
        }
        for (int i = 5; i > database1.returnMovieRating(movieID); i--){
            UI.drawOval(130 + (database1.returnMovieRating(movieID) * 22) + (22 * (5-i)), 385, 20, 20);
        }
    }
    
    /**
     * Display list of all movies
     */
    private void displayMovies(){
        UI.clearGraphics();
        UI.println("\n------ All Movies ------");
        String[] moviesList = database1.getAllMovies();
        for (int i = 0; i < moviesList.length; i++){
             UI.println(moviesList[i]);
        }
        // MAKE SURE TO DRAW A TITLE
        UI.setFontSize(18);
        // DRAW GUI
        for (int i = 0; i < moviesList.length; i++){
            UI.setColor(Color.lightGray);
            UI.fillRect(50, 20 + (55 * i), 250, 50);
            UI.setColor(Color.darkGray);
            UI.drawString(moviesList[i], 75, 50 + (i * 55));
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
        UI.setWindowSize(720, 500);
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

